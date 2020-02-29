package main.scala.learning_flink

import org.apache.flink.api.scala.ExecutionEnvironment
import org.apache.flink.api.scala._

object Review {
  def main(args: Array[String]): Unit = {
    val env: ExecutionEnvironment = ExecutionEnvironment.getExecutionEnvironment
    val data: DataSet[String] = env.fromElements("Gift is better", "Money is best", "Money", "money")
    data.flatMap(_.toLowerCase.split(" "))
      .map((_, 1))
      .groupBy(0)
      .sum(1)
      .print()
  }

}
