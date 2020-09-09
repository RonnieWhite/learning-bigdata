package com.baich.learning_bigdata.learning_spark.spark_sql

import org.apache.spark.SparkConf
import org.apache.spark.sql.{DataFrame, SparkSession}

object SparkSQL2 {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setAppName("ss2").setMaster("local")
    val session: SparkSession = SparkSession.builder().config(conf).getOrCreate()
    val df: DataFrame = session.read.json("E:/data/spark/students.json")
    //    df.show()
    //    df.select("name")
    import session.implicits._
    //    df.select($"name", $"age" + 1).show()
    //    df.filter($"age" > 18).show()
//    df.groupBy($"age").count().show()

    df.createTempView("students")
    session.sql("SELECT avg(age) FROM students").show()
    session.close()
  }

}
