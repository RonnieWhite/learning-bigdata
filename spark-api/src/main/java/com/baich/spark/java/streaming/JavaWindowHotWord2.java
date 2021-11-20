package com.baich.spark.java.streaming;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaPairDStream;
import org.apache.spark.streaming.api.java.JavaReceiverInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import scala.Tuple2;

import java.util.List;

public class JavaWindowHotWord2 {
    public static void main(String[] args) throws Exception {
        SparkConf conf = new SparkConf().setAppName("jwhw2").setMaster("local[2]");
        JavaStreamingContext jsc = new JavaStreamingContext(conf, Durations.seconds(5));
        // leo hello
        JavaReceiverInputDStream<String> log = jsc.socketTextStream("localhost", 9999);
        JavaPairDStream<String, Integer> searchWordCountsDStream = log.map(line -> line.split(" ")[1])
                .mapToPair(word -> new Tuple2<>(word, 1))
                .reduceByKeyAndWindow(new Function2<Integer, Integer, Integer>() {
                    @Override
                    public Integer call(Integer v1, Integer v2) throws Exception {
                        return v1 + v2;
                    }
                }, Durations.seconds(60), Durations.seconds(10));

        JavaPairDStream<String, Integer> finalStream = searchWordCountsDStream.transformToPair(new Function<JavaPairRDD<String, Integer>, JavaPairRDD<String, Integer>>() {
            @Override
            public JavaPairRDD<String, Integer> call(JavaPairRDD<String, Integer> searchWordCountsRDD) throws Exception {
                List<Tuple2<String, Integer>> hotThree = searchWordCountsRDD.mapToPair(info -> new Tuple2<>(info._2, info._1))
                        .sortByKey(false)
                        .mapToPair(info -> new Tuple2<>(info._2, info._1))
                        .take(3);
                for (Tuple2 hot : hotThree) {
                    System.out.println(hot._1 + ":" + hot._2);
                }
                return searchWordCountsRDD;
            }
        });
        finalStream.print();
        jsc.start();
        jsc.awaitTermination();
        jsc.close();
    }
}
