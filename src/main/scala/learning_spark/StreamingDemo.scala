package main.scala.learning_spark

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}

object StreamingDemo {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setMaster("local[2]").setAppName("StreamingDemo")
    val ssc = new StreamingContext(conf, Seconds(5))

    // 读nc -l -p 9999
    // runSocket(ssc)
    // 读本地文件
    runTextFile(ssc)
    ssc.start()
    ssc.awaitTermination()
    //    ssc.stop()
  }

  def runSocket(ssc: StreamingContext) = {
    val data: ReceiverInputDStream[String] = ssc.socketTextStream("localhost", 9999)
    val values: DStream[(String, Int)] = data.flatMap(_.split(" "))
      .map((_, 1))
      .reduceByKey(_ + _)
    values.print()
  }

  def runTextFile(ssc: StreamingContext) = {
    ssc.textFileStream("E:/data/spark/streaming/*")
      .flatMap(_.split(" "))
      .map((_, 1))
      .reduceByKey(_ + _).print()
  }
}
