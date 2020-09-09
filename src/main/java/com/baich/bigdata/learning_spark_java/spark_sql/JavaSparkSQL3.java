package com.baich.bigdata.learning_spark_java.spark_sql;

import org.apache.spark.SparkConf;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class JavaSparkSQL3 {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("jss3").setMaster("local[2]");
        SparkSession spark = SparkSession.builder().config(conf).getOrCreate();
        Dataset<Row> df = spark.read().json("E:/data/spark/students.json");
//        df.show();
//        df.printSchema();
//        df.select("id").show();
//        df.select(col("id").plus(1), col("name")).show();
//        df.filter(col("id").gt(3)).show();
//        df.groupBy(col("age")).count().show();
        df.createOrReplaceTempView("student");
//        Dataset<Row> dataset = spark.sql("SELECT AVG(age) FROM student");
//        Dataset<Row> dataset = spark.sql("SELECT MAX(age) FROM global_temp.student");
//        Dataset<Row> dataset = spark.sql("SHOW databases");
        Dataset<Row> dataset = spark.newSession().sql("SELECT MAX(age) FROM global_temp.student");
        dataset.show();
        spark.close();
    }
}
