package rdd_broadcast_accumulator

import org.apache.spark.rdd.RDD
import org.apache.spark.util.LongAccumulator
import org.apache.spark.{SparkConf, SparkContext}

object AccumulatHorVariable {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setAppName("acc").setMaster("local")
    val sc: SparkContext = new SparkContext(conf)
    val numbers: RDD[Int] = sc.parallelize(Array(1, 3, 5, 7))
    val myAcc: LongAccumulator = sc.longAccumulator("myAcc")
    numbers.foreach(num => myAcc.add(num))
    println(myAcc.value)
  }
}
