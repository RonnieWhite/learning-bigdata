package com.baich.learning_bigdata.practise.offical_doc_code

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Rdd1 {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("rdd1").setMaster("local")
    val sc = new SparkContext(conf)
    //    whole(sc)
    one(sc)
    sc.stop()
  }

  def whole(sc: SparkContext): Unit = {
    val files = sc.wholeTextFiles("/data")
    files.reduceByKey(_ + _).foreach(info => println(info._1 + ":" + info._2))
  }

  def one(sc: SparkContext): Unit = {
    val textFile = sc.textFile("/data/README.md")
    val count = textFile.filter(line => line.contains("Spark")).count()
    print(count)
  }
}
