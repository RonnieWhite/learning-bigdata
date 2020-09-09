package com.baich.learning_bigdata.learning_spark_java.streaming;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaPairDStream;
import org.apache.spark.streaming.api.java.JavaReceiverInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import scala.Tuple2;

import java.util.List;

public class JavaWindowHotWord {
    public static void main(String[] args) throws Exception {
        SparkConf conf = new SparkConf().setAppName("jwhw").setMaster("local[2]");
        JavaStreamingContext jsc = new JavaStreamingContext(conf, Durations.seconds(5));
        JavaReceiverInputDStream<String> searchLogDStream = jsc.socketTextStream("localhost", 9999);
        /**
         * 格式
         * 用户名 搜索词
         * leo hello
         * tom world
         */
        // 将搜索日志给转换成只有一个搜索词即可
        // 将搜索词映射为(searchWord,1)的tuple格式
        JavaPairDStream<String, Integer> searchWordPairDStream = searchLogDStream.map(line -> line.split(" ")[1])
                .mapToPair(word -> new Tuple2<String, Integer>(word, 1));
        // 第二个参数是窗口长度，这里是60s，第三个参数是滑动间隔，这里是10s
        // 也就是说，每隔10s，将最近60s的数据最为一个窗口进行内部的RDD聚合，然后统一对一个RDD进行后续计算
        // 这里的stream都不会立即进行计算，只是放在哪里，等待滑动间隔到了以后进行计算
        // 因为一个batch间隔是5秒，所以之前的60秒，就有12个RDD进行聚合，然后统一执行reduceByKey操作
        // 所以这里的reduceByKeyAndWindow，是针对每个窗口执行计算的，而不是针对某个DStream中的RDD
        JavaPairDStream<String, Integer> searchWordCountsDStream =
                searchWordPairDStream.reduceByKeyAndWindow(new Function2<Integer, Integer, Integer>() {
                    @Override
                    public Integer call(Integer v1, Integer v2) throws Exception {
                        return v1 + v2;
                    }
                }, Durations.seconds(60), Durations.seconds(10));
        JavaPairDStream<String, Integer> finalDStream = searchWordCountsDStream.transformToPair(
                new Function<JavaPairRDD<String, Integer>, JavaPairRDD<String, Integer>>() {
                    @Override
                    public JavaPairRDD<String, Integer> call(JavaPairRDD<String, Integer> searchWordCountsRDD) throws Exception {
                        JavaPairRDD<Integer, String> countSearchWordsRDD = searchWordCountsRDD.mapToPair(new PairFunction<Tuple2<String, Integer>, Integer, String>() {

                            @Override
                            public Tuple2<Integer, String> call(Tuple2<String, Integer> tuple) throws Exception {
                                return new Tuple2<Integer, String>(tuple._2, tuple._1);
                            }
                        });
                        JavaPairRDD<Integer, String> sortedCountSearchWordsRDD = countSearchWordsRDD.sortByKey(false);
                        JavaPairRDD<String, Integer> sortedSearchWordCountsRDD = sortedCountSearchWordsRDD.mapToPair(new PairFunction<Tuple2<Integer, String>, String, Integer>() {
                            @Override
                            public Tuple2<String, Integer> call(Tuple2<Integer, String> tuple) throws Exception {
                                return new Tuple2<String, Integer>(tuple._2, tuple._1);
                            }
                        });
                        List<Tuple2<String, Integer>> hotSearchWordCounts = sortedSearchWordCountsRDD.take(3);
                        for (Tuple2<String, Integer> wordCount : hotSearchWordCounts) {
                            System.out.println(wordCount._1 + ":" + wordCount._2);
                        }
                        return searchWordCountsRDD;
                    }
                });
        // 这个无关紧要，只是为了触发job的执行，所以必须有output操作
        finalDStream.print();
        jsc.start();
        jsc.awaitTermination();
        jsc.close();
    }
}
