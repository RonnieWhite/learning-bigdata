package com.baich.learning_bigdata.learning_flink.course07_time_windows

import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.windowing.assigners.{TumblingEventTimeWindows, TumblingProcessingTimeWindows}
import org.apache.flink.streaming.api.windowing.time.Time

/**
  * 滚动和滑动窗口调用的是timeWindow方法的异构
  * 调用时，slide参数传入时为滑动窗口，不传入时为滚动窗口
  */
object WindowsApp {
  def main(args: Array[String]): Unit = {
    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
    //    tumblingWindows(env)
    slidingWindows(env)
    env.execute()
  }

  def tumblingWindows(env: StreamExecutionEnvironment): Unit = {
    val data: DataStream[String] = env.socketTextStream("localhost", 9999)
    data.flatMap(_.toLowerCase().split(" "))
      .map((_, 1))
      .keyBy(0)
      .timeWindow(Time.seconds(5))
      .sum(1)
      .print()
  }

  def slidingWindows(env: StreamExecutionEnvironment): Unit = {
    val data: DataStream[String] = env.socketTextStream("localhost", 9999)
    data.flatMap(_.toLowerCase().split(" "))
      .map((_, 1))
      .keyBy(0)
      .timeWindow(Time.seconds(10), Time.seconds(5))
      .sum(1)
      .print()
  }

}
