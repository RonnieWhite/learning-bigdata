package com.white.learning_bigdata.learning_spark_java.rdd_broadcast_accumulator;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

public class persistDemo {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("pd").setMaster("local[1]");
        JavaSparkContext jsc = new JavaSparkContext(conf);
        JavaRDD<String> rdd = jsc.textFile("E:/data/spark/chinese.txt").cache();
        long beginTime = System.currentTimeMillis();
        long count = rdd.count();
        System.out.println(count);
        long endTime = System.currentTimeMillis();
        System.out.println("cost:" + (endTime - beginTime) + "milliseconds");
        beginTime = System.currentTimeMillis();
        count = rdd.count();
        System.out.println(count);
        endTime = System.currentTimeMillis();
        System.out.println("cost:" + (endTime - beginTime) + "milliseconds.");
        jsc.close();
    }
}
