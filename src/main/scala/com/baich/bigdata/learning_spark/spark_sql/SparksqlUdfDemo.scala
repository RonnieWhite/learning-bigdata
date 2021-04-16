package com.baich.bigdata.learning_spark.spark_sql

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

/**
  * Author: Chenghui Bai
  * Date: 2021/4/14 22:59
  * ProjectName: learning-bigdata
  * PackageName: com.baich.bigdata.learning_spark.spark_sql
  * ClassName: SparksqlUdfDemo
  * Version:
  * Description:
  */
object SparksqlUdfDemo {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("SparksqlUdfDemo").setMaster("local")
    val spark = SparkSession.builder().config(conf).getOrCreate()
    spark.udf.register("addStr", (param: String) => {
      "Str" + param
    })
    spark.read.json("file:///E:/data/data.json").createOrReplaceTempView("user")
    spark.sql("select addStr(name) from user").show()
    spark.stop()
  }
}
