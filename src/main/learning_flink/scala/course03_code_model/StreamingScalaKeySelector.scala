package main.learning_flink.scala.course03_code_model

import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}
import org.apache.flink.streaming.api.windowing.time.Time
import org.apache.flink.api.scala._

object StreamingScalaKeySelector {
  def main(args: Array[String]): Unit = {
    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
    val text: DataStream[String] = env.socketTextStream("localhost", 9999)
    text.flatMap(_.toLowerCase.split(" "))
      .filter(_.nonEmpty)
      .map(x => WC(x, 1))
      .keyBy(_.word)
      .timeWindow(Time.seconds(5))
      .sum("count")
      .print()
      .setParallelism(1)
    env.execute()
  }

  case class WC(word: String, count: Int)

}
