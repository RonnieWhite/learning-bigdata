package course05_datastream

import org.apache.flink.api.common.JobExecutionResult
import org.apache.flink.api.common.accumulators.LongCounter
import org.apache.flink.api.common.functions.{MapFunction, RichMapFunction}
import org.apache.flink.configuration.Configuration
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}
import org.apache.flink.streaming.api.scala._

object DataStreamSourceApp {
  def main(args: Array[String]): Unit = {
    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
    //    socketFunc(env)
    // 无并行
    //    NonParallelSourceFunction(env)
    // 并行
//    ParallelSourceFunction(env)
    // 增强并行
    RichParallelSourceFunction(env)
    env.execute("DataStreamSourceApp")
  }

  def socketFunc(env: StreamExecutionEnvironment): Unit = {
    val data: DataStream[String] = env.socketTextStream("localhost", 9999)
    data.print().setParallelism(2)

  }

  def NonParallelSourceFunction(env: StreamExecutionEnvironment): Unit = {
    val data: DataStream[Long] = env.addSource(new CustomNonParallelSourceFunction)
    data.print()
  }

  def ParallelSourceFunction(env: StreamExecutionEnvironment): Unit = {
    val data: DataStream[Long] = env.addSource(new CustomParallelSourceFunction).setParallelism(2)
    data.print()
  }

  def RichParallelSourceFunction(env: StreamExecutionEnvironment): Unit = {
    val data: DataStream[Long] = env.addSource(new RichCustomParallelSourceFunction).setParallelism(2)
    data.print()
  }

}
