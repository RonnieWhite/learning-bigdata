package main.scala.learning_flink.course04_dataset

import org.apache.flink.api.common.JobExecutionResult
import org.apache.flink.api.common.accumulators.LongCounter
import org.apache.flink.api.common.functions.RichMapFunction
import org.apache.flink.api.scala.ExecutionEnvironment
import org.apache.flink.api.scala._
import org.apache.flink.configuration.Configuration


object CounterApp {
  def main(args: Array[String]): Unit = {
    val env: ExecutionEnvironment = ExecutionEnvironment.getExecutionEnvironment
    val data: DataSet[String] = env.fromElements("hadoop", "spark", "flink", "pyspark", "storm")
    val info: DataSet[String] = data.map(new RichMapFunction[String, String]() {
      // 定义计数器
      private val counter = new LongCounter()

      override def open(parameters: Configuration): Unit = {
        // 注册计数器
        getRuntimeContext.addAccumulator("ele-counts-scala", counter)
      }

      override def map(in: String): String = {
        counter.add(1)
        in
      }
    })
    info.writeAsText("E:/data/flink/output/").setParallelism(2)
    val jobResult: JobExecutionResult = env.execute("CounterApp")
    // 获取计数器
    val num: Long = jobResult.getAccumulatorResult[Long]("ele-counts-scala")
    println("num: " + num)
  }

}
