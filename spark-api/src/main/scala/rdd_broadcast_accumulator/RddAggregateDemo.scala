package rdd_broadcast_accumulator

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Author: Chenghui Bai
  * Date: 2021/4/15 11:59
  * ProjectName: learning-bigdata
  * PackageName: com.baich.bigdata.learning_spark.rdd_broadcast_accumulator
  * ClassName: RddAggregateDemo
  * Version:
  * Description:
  */
object RddAggregateDemo {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("RddAggregateDemo").setMaster("local")
    val sc = new SparkContext(conf)
    val rdd = sc.makeRDD(1 until 10)
    val i = rdd.aggregate(0)(_ - _, _ + _)
    println(i)
    sc.stop()
  }

}
