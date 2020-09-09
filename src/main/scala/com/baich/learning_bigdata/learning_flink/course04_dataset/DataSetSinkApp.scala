package com.baich.learning_bigdata.learning_flink.course04_dataset

import org.apache.flink.api.scala.ExecutionEnvironment
import org.apache.flink.api.scala._
import org.apache.flink.core.fs.FileSystem.WriteMode

object DataSetSinkApp {
  def main(args: Array[String]): Unit = {
    val env: ExecutionEnvironment = ExecutionEnvironment.getExecutionEnvironment
    val data: Range.Inclusive = 1.to(10)
    val text = env.fromCollection(data)
    val filePath = "E:/data/flink/output/"
    // WriteMode.OVERWRITE 覆盖写入
    text.writeAsText(filePath, WriteMode.OVERWRITE) // 并行度为1，写入文件
    text.writeAsText(filePath, WriteMode.OVERWRITE).setParallelism(2) // 并行度为2，会生成E:/data/flink/output/文件夹，在下面生成2个文件
    env.execute("sinkApp")
  }

}
