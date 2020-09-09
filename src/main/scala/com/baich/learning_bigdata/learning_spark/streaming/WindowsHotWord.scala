package com.baich.learning_bigdata.learning_spark.streaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}

object WindowsHotWord {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setAppName("whw").setMaster("local[2]")
    val sc: StreamingContext = new StreamingContext(conf, Seconds(5))
    val hotWordsStream: ReceiverInputDStream[String] = sc.socketTextStream("localhost", 9999)
    val searchWordPairsDStream: DStream[(String, Int)] = hotWordsStream.map(line => (line.split(" ")(1), 2))
    val searchWordCountsDStream: DStream[(String, Int)] = searchWordPairsDStream.reduceByKeyAndWindow(
      (v1: Int, v2: Int) => v1 + v2,
      Seconds(60),
      Seconds(10))
    val finalDStream: DStream[(String, Int)] = searchWordCountsDStream.transform(searchWordCountsRDD => {
      val topThreeSearchWordCounts: Array[(String, Int)] = searchWordCountsRDD.map(tuple => (tuple._2, tuple._1))
        .sortByKey(false: Boolean)
        .map(tuple => (tuple._2, tuple._1))
        .take(3)
      for (tuple <- topThreeSearchWordCounts) {
        println(tuple)
      }
      searchWordCountsRDD
    })
    finalDStream.print()
    sc.start()
    sc.awaitTermination()
    sc.stop()
  }
}
