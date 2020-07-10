package practise.offical_doc_code

import org.apache.spark.{SparkConf, SparkContext}

object Rdd1 {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("rdd1").setMaster("local")
    val sc = new SparkContext(conf)
    val textFile = sc.wholeTextFiles("/data")
    textFile.reduceByKey(_ + _).foreach(info => println(info._1 + " " + info._2))
    sc.stop()
  }

}
