package com.baich.bigdata.learning_spark.rdd_broadcast_accumulator

import org.apache.spark.{SparkConf, SparkContext}

object review {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setAppName("review").setMaster("local")
    val sc: SparkContext = SparkContext.getOrCreate(conf)
    sc.textFile("E:/data/spark/README.md").flatMap(s => s.split(" "))
      .map(s => (s, 1))
      //      .reduceByKey(_ + _)
      .reduceByKey((a, b) => a + b)
      .collect().foreach {
      println
    }
  }

}
