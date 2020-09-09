package com.baich.bigdata.learning_spark_java.rdd_broadcast_accumulator;

import org.apache.spark.Accumulator;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import java.util.Arrays;
import java.util.List;


public class JavaAccumulatorVariable {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("av").setMaster("local");
        JavaSparkContext sc = new JavaSparkContext(conf);
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        JavaRDD<Integer> numbers = sc.parallelize(list);
        runFilter(numbers);
        sc.close();
    }

    public static void runAccu(JavaRDD<Integer> numbers, JavaSparkContext sc) {
        final Accumulator<Integer> accumulator = sc.accumulator(0);
        numbers.foreach(s -> accumulator.add(s));
        System.out.println(accumulator);
    }

    public static void runFilter(JavaRDD<Integer> numbers) {
        numbers.filter(s -> s % 2 == 0).foreach(s -> System.out.println(s));
    }


}
