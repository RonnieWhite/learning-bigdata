package learning_flink.course04_dataset

import java.io.File
import java.util

import org.apache.commons.io.FileUtils
import org.apache.flink.api.common.functions.RichMapFunction
import org.apache.flink.api.scala.ExecutionEnvironment
import org.apache.flink.api.scala._
import org.apache.flink.configuration.Configuration

object DistributedCacheApp {
  def main(args: Array[String]): Unit = {
    val env: ExecutionEnvironment = ExecutionEnvironment.getExecutionEnvironment
    val data: DataSet[String] = env.fromElements("hadoop", "spark", "shell")
    // 1. 注册一个文件
    env.registerCachedFile("E:/data/flink/input/abc.txt", "white_scala_dc")
    data.map(new RichMapFunction[String, String] {
      // 2. 在open方法中获取分布式缓存的内容即可
      override def open(parameters: Configuration): Unit = {
        val dcFile: File = getRuntimeContext.getDistributedCache.getFile("white_scala_dc")
        val lines: util.List[String] = FileUtils.readLines(dcFile) // java
        /**
          * 此时出现一个异常，java集合和scala集合不兼容，因此取数会报错
          */
        //        for (ele: String <- lines) {
        //          //scala
        //          println(ele)
        //        }
        // 可以使用转换
        import scala.collection.JavaConverters._
        for (ele: String <- lines.asScala) {
          print(ele)
        }
      }

      override def map(value: String): String = {
        value
      }
    }).print()
  }

}
