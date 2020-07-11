package com.white.learning_bigdata.learning_spark_java.spark_sql;

import org.apache.spark.SparkConf;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class JavaSparkSQLHive {
    public static void main(String[] args) throws Exception {
        SparkConf conf = new SparkConf().setAppName("spark-hive").setMaster("local[2]").set("catalogImplementation", "in-memory");
        SparkSession spark = SparkSession.builder().config(conf).enableHiveSupport().getOrCreate();
        Dataset<Row> dataset = spark.sql("SELECT * FROM ods_weblog_origin");
        dataset.createOrReplaceTempView("tmp_abc");
        spark.sql("SHOW databases").show();
        System.out.println("====");
        spark.sql("SHOW tables").show();
//        spark.sql("INSERT into table abc select * from tmp_abc");
        spark.close();
    }
}
