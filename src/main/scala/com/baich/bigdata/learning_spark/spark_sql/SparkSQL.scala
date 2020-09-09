package com.baich.bigdata.learning_spark.spark_sql

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.{DataFrame, SQLContext, SparkSession}

object SparkSQL {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setAppName("sql").setMaster("local")
    //    val sc: SparkContext = new SparkContext(conf)
    //    new SQLContext(sc)
    val session: SparkSession = SparkSession.builder().appName("sqlT").config(conf).getOrCreate()
    val df: DataFrame = session.read.json("E:/data/spark/students.json")
    // 全表打印显示
    //    df.show()
    // 元数据（schema）
//    df.printSchema()
    // 查询,选择列
    df.select("name").show()
    session.close()
  }
}
