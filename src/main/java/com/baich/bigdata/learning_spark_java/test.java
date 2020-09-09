package com.baich.bigdata.learning_spark_java;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import java.util.List;

public class test {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("t1").setMaster("local[2]");
        JavaSparkContext spark = new JavaSparkContext(conf);
        JavaRDD<String> rdd = spark.textFile("hdfs://vm01:9000/data");
        List<String> collect = rdd.collect();
        System.out.println(collect);
    }
}
