package com.baich.learning_bigdata.learning_spark_java.spark_sql;

import org.apache.spark.SparkConf;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import static org.apache.spark.sql.functions.col;

public class JavaSparkSQL2 {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("jss").setMaster("local");
        SparkSession session = SparkSession.builder().config(conf).getOrCreate();
        Dataset<Row> students = session.read().json("E:/data/spark/students.json");
        // 显示全表
//        students.show();
        // 打印元数据
//        students.printSchema();
        // 选择数据列
//        students.select("age").show();
//        students.select(col("age")).show();
        // 选择多列，且age列+1
//        students.select(col("name"), col("age").plus(1)).show();
        students.filter(col("age").gt(20)).show();
        session.close();
    }
}
