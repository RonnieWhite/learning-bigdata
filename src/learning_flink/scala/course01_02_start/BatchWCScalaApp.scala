package learning_flink.scala.course01_02_start

import org.apache.flink.api.scala.{DataSet, ExecutionEnvironment}
import org.apache.flink.api.scala._

object BatchWCScalaApp {
  def main(args: Array[String]): Unit = {
    val input: String = "E:/data/flink/input"
    val env: ExecutionEnvironment = ExecutionEnvironment.getExecutionEnvironment
    val text: DataSet[String] = env.readTextFile(input)
    text.flatMap(_.toLowerCase.split(" "))
      .filter(_.nonEmpty)
      .map((_, 1))
      .groupBy(0)
      .sum(1)
      .print()
  }
}
