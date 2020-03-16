package main.scala.learning_spark.streaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.ReceiverInputDStream
import org.apache.spark.streaming.{Seconds, StreamingContext}

object WindowsHotWord {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setAppName("whw").setMaster("local[2]")
    val sc: StreamingContext = new StreamingContext(conf, Seconds(5))
    val hotWordsStream: ReceiverInputDStream[String] = sc.socketTextStream("localhost", 9999)
    hotWordsStream.map(line => (line.split(" ")(1), 1))
  }
}
