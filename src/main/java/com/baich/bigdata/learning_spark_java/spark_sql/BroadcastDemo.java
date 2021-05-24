package com.baich.bigdata.learning_spark_java.spark_sql;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.sql.SparkSession;

public class BroadcastDemo {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("bcv").setMaster("local");
        SparkSession spark = SparkSession.builder().config(conf).getOrCreate();
        JavaSparkContext jsc = JavaSparkContext.fromSparkContext(spark.sparkContext());
        Broadcast<Integer> broadcast = jsc.broadcast(1);
        System.out.println(broadcast.value());
    }
}
