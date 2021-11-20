package com.baich.spark.java.spark_sql;

import org.apache.spark.SparkConf;
import org.apache.spark.sql.SparkSession;

public class JavaSparkSQLHive {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("JavaSparkSQLHive").setMaster("local");
        SparkSession spark = SparkSession.builder()
                .config(conf)
                .enableHiveSupport()
                .getOrCreate();
        spark.sql("SHOW databases").show();
        spark.close();
    }
}
