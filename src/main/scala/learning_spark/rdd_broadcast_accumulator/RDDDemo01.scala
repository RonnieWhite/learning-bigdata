package main.scala.learning_spark.rdd_broadcast_accumulator

import org.apache.spark.rdd.RDD
import org.apache.spark.util.LongAccumulator
import org.apache.spark.{SparkConf, SparkContext, broadcast}

object RDDDemo01 {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setAppName("Demo01").setMaster("local")
    val sc = new SparkContext(conf)
    //    readText(sc)
    //    broadcastDemo(sc)
    //    val data = Array(1, 2, 3, 4, 5)
    //    val distData: RDD[Int] = sc.parallelize(data)
    //    val ints: Array[Int] = distData.collect()
    //    for (i <- 0 to ints.length) {
    //      println(i)
    //    }
    //    accumDemo(sc)
    accumDemo2(sc)
    sc.stop()
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

  def accumDemo(sc: SparkContext): Unit = {
    val accumulator: LongAccumulator = sc.longAccumulator("ha")
    print(accumulator.value)
  }

  def accumDemo2(sc: SparkContext): Unit = {
    val counter: LongAccumulator = sc.longAccumulator("counter")
    val sum: Int = sc.parallelize(Array(1, 3, 5, 7, 9, 2, 4, 6, 8, 10), 2).filter(n => {
      if (n % 2 == 1) counter.add(1L)
      n % 2 == 0
    }).reduce(_ + _)
    println(sum)
    println(counter.value)
  }
}
