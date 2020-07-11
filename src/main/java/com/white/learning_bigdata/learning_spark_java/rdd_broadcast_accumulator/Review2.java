package com.white.learning_bigdata.learning_spark_java.rdd_broadcast_accumulator;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import scala.Tuple2;

import java.util.Arrays;
import java.util.Iterator;

public class Review2 {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("review2").setMaster("local[1]");
        JavaSparkContext jsc = new JavaSparkContext(conf);
        JavaRDD<String> stringJavaRDD = jsc.textFile("E:/data/spark/streaming.txt");
        JavaPairRDD<String, Integer> rdd = stringJavaRDD.flatMap(new FlatMapFunction<String, String>() {

            @Override
            public Iterator<String> call(String s) throws Exception {
                String[] words = s.toLowerCase().split(" ");
                return Arrays.asList(words).iterator();
            }
        }).mapToPair(new PairFunction<String, String, Integer>() {
            @Override
            public Tuple2<String, Integer> call(String s) throws Exception {
                return new Tuple2<>(s, 1);
            }
        }).reduceByKey(new Function2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer v1, Integer v2) throws Exception {
                return v1 + v2;
            }
        }).mapToPair(new PairFunction<Tuple2<String, Integer>, Integer, String>() {
            @Override
            public Tuple2<Integer, String> call(Tuple2<String, Integer> tuple) throws Exception {
                return new Tuple2<>(tuple._2, tuple._1);
            }
        }).sortByKey(false).mapToPair(new PairFunction<Tuple2<Integer, String>, String, Integer>() {
            @Override
            public Tuple2<String, Integer> call(Tuple2<Integer, String> tuple) throws Exception {
                return new Tuple2<>(tuple._2, tuple._1);
            }
        });
        System.out.println(rdd.collect());
        jsc.close();
    }
}
