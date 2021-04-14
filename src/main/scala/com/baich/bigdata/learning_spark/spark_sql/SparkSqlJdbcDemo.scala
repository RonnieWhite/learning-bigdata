package com.baich.bigdata.learning_spark.spark_sql

import java.util.Properties

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

/**
  * Author: Chenghui Bai
  * Date: 2021/4/14 16:27
  * ProjectName: learning-bigdata
  * PackageName: com.baich.bigdata.learning_spark.spark_sql
  * ClassName: SparkSqlJdbcDemo
  * Version:
  * Description:
  */
object SparkSqlJdbcDemo {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local").setAppName("SparkSqlJdbcDemo")
    val spark = SparkSession.builder().config(conf).getOrCreate()
    val properties = new Properties()
    properties.setProperty("user", "root")
    properties.setProperty("password", "123456")
    spark.read.jdbc("jdbc:mysql://vm01:3306/ods", "t_user", properties).show()
    spark.stop()
  }

}
