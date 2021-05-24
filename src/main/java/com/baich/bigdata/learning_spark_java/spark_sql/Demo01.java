package com.baich.bigdata.learning_spark_java.spark_sql;

import org.apache.spark.SparkConf;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class Demo01 {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf();
        conf.setAppName("demo01").setMaster("local[4]");
        SparkSession spark = SparkSession.builder().config(conf).getOrCreate();
        Dataset<Row> ds = spark.read().json("");
    }
}
