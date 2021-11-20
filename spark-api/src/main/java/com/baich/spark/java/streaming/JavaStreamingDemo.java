package com.baich.spark.java.streaming;

import org.apache.spark.SparkConf;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaPairDStream;
import org.apache.spark.streaming.api.java.JavaReceiverInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import scala.Tuple2;

import java.util.Arrays;

public class JavaStreamingDemo {
    public static void main(String[] args) throws Exception {
        SparkConf conf = new SparkConf().setAppName("streaming").setMaster("local[2]");
        JavaStreamingContext jsc = new JavaStreamingContext(conf, new Duration(5000));
//        runSocket(jsc);
        runHdfsFile(jsc);
        jsc.start();
        jsc.awaitTermination();
        jsc.close();
    }

    public static void runSocket(JavaStreamingContext jsc) {
        JavaReceiverInputDStream<String> data = jsc.socketTextStream("localhost", 9999);
        JavaPairDStream<String, Integer> result = data.flatMap(line -> Arrays.asList(line.split(" ")).iterator())
                .mapToPair(word -> new Tuple2<>(word, 1))
                .reduceByKey((a, b) -> a + b);
        result.print();
    }

    public static void runHdfsFile(JavaStreamingContext jsc) {
        JavaDStream<String> textFileStream = jsc.textFileStream("hdfs://myspark:9000/spark");
        JavaPairDStream<String, Integer> result = textFileStream.flatMap(line -> Arrays.asList(line.split(" ")).iterator())
                .mapToPair(word -> new Tuple2<>(word, 1))
                .reduceByKey((a, b) -> a + b);
        result.print();
    }


}
