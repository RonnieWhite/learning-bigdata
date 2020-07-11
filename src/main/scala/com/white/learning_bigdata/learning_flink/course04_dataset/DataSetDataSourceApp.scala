package com.white.learning_bigdata.learning_flink.course04_dataset

import org.apache.flink.api.scala.ExecutionEnvironment
import org.apache.flink.api.scala._
import org.apache.flink.configuration.Configuration

object DataSetDataSourceApp {
  def main(args: Array[String]): Unit = {
    val env: ExecutionEnvironment = ExecutionEnvironment.getExecutionEnvironment
    // 创建数据集合
    //    fromCollection(env)
    // 读text文件
    //    readTextFile(env)
    // 读csv文件
    //    readCsvFile(env)
    //    递归读取数据
//    readRecursiveFiles(env)
    // 读取压缩文件
    readCompressionFiles(env)

  }

  def fromCollection(env: ExecutionEnvironment): Unit = {
    val data = 1 to 10
    env.fromCollection(data).print()

  }

  def readTextFile(env: ExecutionEnvironment): Unit = {
    env.readTextFile("E:/data/flink/input").print()
  }

  def readCsvFile(env: ExecutionEnvironment): Unit = {
    // 用tulpe
    env.readCsvFile[(String, Int, String)]("E:/data/flink/input/person.csv", ignoreFirstLine = true).print()
    // 用pojo
    env.readCsvFile[Person]("E:/data/flink/input/person.csv", ignoreFirstLine = true, pojoFields = Array("name", "age", "job")).print()
  }

  // 递归读取文件
  def readRecursiveFiles(env: ExecutionEnvironment): Unit = {
    val filePath = "E:/data/flink/input/recursive_test"
    //    env.readTextFile(filePath).print()
    val parameters = new Configuration
    parameters.setBoolean("recursive.file.enumeration", true)
    env.readTextFile(filePath).withParameters(parameters).print()

  }

  def readCompressionFiles(env: ExecutionEnvironment): Unit = {
    val filePath = "E:/data/flink/input/abc.zip"
    env.readTextFile(filePath).print()
  }
}
