package com.baich.bigdata.learning_spark.streaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}

object UpdateStateByKeyWC {
  def main(args: Array[String]): Unit = {
    System.setProperty("HADOOP_USER_NAME", "sparkuser")
    val conf: SparkConf = new SparkConf().setAppName("usbw").setMaster("local[2]")
    val sc: StreamingContext = new StreamingContext(conf, Seconds(2))
    val data: ReceiverInputDStream[String] = sc
      .socketTextStream("localhost", 9999)
    sc.checkpoint("hdfs://myspark:9000/spark/update")
    val stream: DStream[(String, Int)] = data.flatMap(_.split(" "))
      .map((_, 1))
      .updateStateByKey((values: Seq[Int], state: Option[Int]) => {
        var newValue: Int = state.getOrElse(0)
        for (value <- values) {
          newValue += value
        }
        Option(newValue)
      })
    stream.print()
    sc.start()
    sc.awaitTermination()
    sc.stop()
  }
}
