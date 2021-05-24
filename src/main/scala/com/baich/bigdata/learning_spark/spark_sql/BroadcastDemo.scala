package com.baich.bigdata.learning_spark.spark_sql

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

object BroadcastDemo {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("scalaBd").setMaster("local")
    val spark = SparkSession.builder().config(conf).getOrCreate()
  }

}
