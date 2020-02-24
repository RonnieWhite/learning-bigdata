package learning_flink.scala_demo

import org.apache.flink.api.scala.ExecutionEnvironment
// 隐式值转换
import org.apache.flink.api.scala._

object BatchWCScalaApp {
  def main(args: Array[String]): Unit = {
    val input: String = "E:/data/flink/input"
    val env: ExecutionEnvironment = ExecutionEnvironment.getExecutionEnvironment
    val text = env.readTextFile(input)
    text.flatMap(_.toLowerCase.split(" "))
      .filter(_.nonEmpty)
      .map((_, 1))
      .groupBy(0)
      .sum(1)
      .print()
  }
}
