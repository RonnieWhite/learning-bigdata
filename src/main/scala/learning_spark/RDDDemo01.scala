package main.scala.learning_spark

import akka.stream.scaladsl.Broadcast
import org.apache.spark.SparkContext
import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.broadcast

object RDDDemo01 {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setAppName("Demo01").setMaster("local")
    val sc = new SparkContext(conf)
    readText(sc)
    //    broadcastDemo(sc)
    //    val data = Array(1, 2, 3, 4, 5)
    //    val distData: RDD[Int] = sc.parallelize(data)
    //    val ints: Array[Int] = distData.collect()
    //    for (i <- 0 to ints.length) {
    //      println(i)
    //    }
  }

  def readText(sc: SparkContext): Unit = {
    //    val filePath = "hdfs://myspark:9000/spark/README.md"
    val filePath = "E:/data/spark/README.md"
    val unit: RDD[Int] = sc.textFile(filePath).map(x => x.length)
    //    val counter: Long = unit.filter(x => x.contains("spark")).count()
    val int: Int = unit.reduce((a, b) => a + b)
    println(int)
  }

  def broadcastDemo(sc: SparkContext): Unit = {
    val broadcastVar: broadcast.Broadcast[Array[Int]] = sc.broadcast(Array(1, 2, 3))
    val value: Array[Int] = broadcastVar.value
    println(value)
  }
}
