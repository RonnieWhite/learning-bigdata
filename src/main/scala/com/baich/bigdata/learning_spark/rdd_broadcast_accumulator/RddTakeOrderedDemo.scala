package com.baich.bigdata.learning_spark.rdd_broadcast_accumulator

import org.apache.spark.{Dependency, SparkConf, SparkContext}

/**
  * Author: Chenghui Bai
  * Date: 2021/4/15 9:41
  * ProjectName: learning-bigdata
  * PackageName: com.baich.bigdata.learning_spark.rdd_broadcast_accumulator
  * ClassName: RddDemo02
  * Version:
  * Description:
  */
object RddTakeOrderedDemo {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local").setAppName("RddTakeOrderedDemo")
    val sc = new SparkContext(conf)
    //    sc.textFile("file:///E:/data/data.txt", 20)
    val rdd1 = sc.makeRDD(1 to 10)
    val rdd2 = rdd1.map(_ * 10)
    val ord = new MyOrdering()
    rdd2.takeOrdered(3)(ord).foreach(println)
    //    val dependencies = rdd2.dependencies
    //    println(dependencies)
    //    rdd1.saveAsTextFile("file:///E:/data/result")
    //    sc.textFile("file:///E:/data/data.txt").map(line=>line.split(","))
    sc.stop()
  }

  class MyOrdering extends Ordering[Int] {
    override def compare(x: Int, y: Int): Int = {
      if (x < y) 1 else if (x == y) 0 else -1
    }
  }

}
