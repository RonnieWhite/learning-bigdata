package com.baich.bigdata.learning_spark.spark_sql

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

/**
  * Author: Chenghui Bai
  * Date: 2021/4/14 15:41
  * ProjectName: learning-bigdata
  * PackageName: com.baich.bigdata.learning_spark.spark_sql
  * ClassName: SparkSqlJsonDemo
  * Version:
  * Description:
  */
object SparkSqlJsonDemo {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("SparkSqlJsonDemo").setMaster("local")
    val spark = SparkSession.builder().config(conf).getOrCreate()
    val df = spark.read.json("file:///E:/data/data.json")
    df.createOrReplaceTempView("player")
    spark.sql("select name,'USA' AS team, num from player").show()
    spark.stop()
  }
}
