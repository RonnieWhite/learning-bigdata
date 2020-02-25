package learning_flink.scala.course03_code_model

import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.api.windowing.time.Time

object StreamingWCScalaApp {
  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    val text = env.socketTextStream("localhost", 9999)

    text.flatMap(_.toLowerCase.split(" "))
      .filter(_.nonEmpty)
      .map(x => WC(x.toLowerCase(), 1))
      .keyBy("word")
      .timeWindow(Time.seconds(5))
      .sum("count")
      .print()
      .setParallelism(1)
    env.execute()
  }

  case class WC(word: String, count: Int)

}
