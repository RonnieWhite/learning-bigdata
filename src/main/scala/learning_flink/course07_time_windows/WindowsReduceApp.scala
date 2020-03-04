package main.scala.learning_flink.course07_time_windows

import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.windowing.time.Time

object WindowsReduceApp {
  def main(args: Array[String]): Unit = {
    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
    val data: DataStream[String] = env.socketTextStream("localhost", 9999)
    data.flatMap(_.split(","))
      .map(x => (1, x.toInt))
      .keyBy(0)
      .timeWindow(Time.seconds(5))
      .reduce((v1, v2) => { // 不是等待窗口所有的数据进行一次性处理，而是数据两两处理
        (v1._1, v1._2 + v2._2)
      }).print()
    env.execute()
  }
}
