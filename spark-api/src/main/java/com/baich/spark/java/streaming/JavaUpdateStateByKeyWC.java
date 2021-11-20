package com.baich.spark.java.streaming;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.Optional;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaPairDStream;
import org.apache.spark.streaming.api.java.JavaReceiverInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import scala.Tuple2;

import java.util.Arrays;
import java.util.List;

public class JavaUpdateStateByKeyWC {
    public static void main(String[] args) throws Exception {
        System.setProperty("HADOOP_USER_NAME", "sparkuser");
        SparkConf conf = new SparkConf().setAppName("usbk").setMaster("local[2]");
        JavaStreamingContext jsc = new JavaStreamingContext(conf, Durations.seconds(2));
        // 要使用updateStateByKey算子，就必须设置一个checkpoint目录，开启checkpoint机制
        // 这样才能把每个key对应的state除了在内存中有，那么是不是也要checkpoint一份
        // 因为长期保存一份key的state的话，spark streaming是要求必须用checkpoint的，
        // 以便于在内存数据丢失的时候可以在checkpoint中恢复数据。
        // 开启checkpoint机制很简单，只要调用jsc的checkpoint()方法，设置一个hdfs目录即可
        jsc.checkpoint("hdfs://myspark:9000/spark/update");
        // 然后先实现基础的wordcount逻辑
        JavaReceiverInputDStream<String> data = jsc.socketTextStream("localhost", 9999);
        JavaPairDStream<String, Integer> stream = (JavaPairDStream<String, Integer>) data
                .flatMap(line -> Arrays.asList(line.split(" ")).iterator())
                .mapToPair(word -> new Tuple2<>(word, 1))
                // 这里，如果需要统计每个单词的全局计数，则到现在为止一个单词的出现次数，
                // 就必须基于redis缓存，或者mysql这种db，来实现累加。
                // updateStateByKey可实现通过直接从spark维护的一份单词的全局统计次数
                .updateStateByKey(new Function2<List<Integer>, Optional<Integer>, Optional<Integer>>() {
                    @Override
                    public Optional<Integer> call(List<Integer> values, Optional<Integer> state) throws Exception {
                        // values相当于这个batch中，这个key的新值，可能有多个，所以是数组
                        // state参数是这个key之前的状态，其中反省的类型是自己指定的
                        // 先定义一个计数器
                        Integer newValue = 0;
                        if (state.isPresent()) {
                            newValue = state.get();
                        }
                        for (Integer value : values) {
                            newValue += value;
                        }
                        return Optional.of(newValue);
                    }
                });
//                .reduceByKey((a, b) -> a + b);
        stream.print();
        jsc.start();
        jsc.awaitTermination();
        jsc.close();
    }
}
