package com.baich.spark.java.spark_sql;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;

public class JavaSparkSQL {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("DataFrameCreate").setMaster("local");
        JavaSparkContext sc = new JavaSparkContext(conf);
        SQLContext sqlContext = new SQLContext(sc);
        Dataset<Row> students = sqlContext.read().json("E:/data/spark/students.json");
        students.show();
    }

}
