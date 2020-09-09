package com.baich.bigdata.learning_flink.course08_connectors

import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}
import org.apache.flink.streaming.connectors.fs.StringWriter
import org.apache.flink.streaming.connectors.fs.bucketing.{BucketingSink, DateTimeBucketer}

object FileSystemConnectorApp {
  def main(args: Array[String]): Unit = {
    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
    val data: DataStream[String] = env.socketTextStream("localhost", 9999)
    System.setProperty("HADOOP_USER_NAME", "sparkuser")
    val filePath = "hdfs://192.168.30.150:9000/flink_data"
    val sink = new BucketingSink[String](filePath)
    sink.setBucketer(new DateTimeBucketer[String]("yyyy-MM-dd--HHmm"))
    sink.setWriter(new StringWriter())
    sink.setBatchRolloverInterval(2000)
    data.addSink(sink)
    env.execute("FileSystemConnectorApp")
  }

}
