package learning_flink.course07_time_windows

import org.apache.flink.streaming.api.TimeCharacteristic
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment

object HowToSetTime {
  def main(args: Array[String]): Unit = {
    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
    // 设置处理时间类型
    env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime)
  }

}
