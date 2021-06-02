package com.baich.bigdata.learning_spark_java.spark_sql;

import org.apache.spark.SparkConf;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class SparkSessionKafkaConsumer {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setMaster("local").setAppName("sskc");
        SparkSession spark = SparkSession.builder().config(conf).getOrCreate();
        Dataset<Row> ds = spark.readStream().format("kafka")
                .option("kafka.bootstrap.servers", "vm01:9092")
                .option("subscribe", "test")
                .load();
//        ds.printSchema();
        Dataset<Row> dataset = ds.selectExpr("CAST(key AS STRING)", "CAST(value AS STRING)");
        dataset.printSchema();
        dataset.select("value").writeStream().format("text").outputMode("append").option("path", "file:///D:/data/res").start();

    }
}
