package com.baich.bigdata.learning_spark.rdd_broadcast_accumulator

import org.apache.spark.broadcast.Broadcast
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object BroadCastVariable {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setAppName("bcv").setMaster("local")
    val sc: SparkContext = new SparkContext(conf)
    val array: Array[Int] = Array(1, 2, 3, 4, 5, 6)
    val factor: Int = 3
    val factorBroadcast: Broadcast[Int] = sc.broadcast(factor)
    val multipleNumbers: RDD[Int] = sc.parallelize(array).map(_ * factorBroadcast.value)
    multipleNumbers.foreach(
      println(_)
    )
  }
}
