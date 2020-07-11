package com.white.learning_bigdata.learning_flink.course05_datastream.task

import org.apache.flink.api.common.functions.MapFunction
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}
import org.apache.flink.streaming.api.scala._

object CustomSinkToMySQL {
  def main(args: Array[String]): Unit = {
//    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
//    val data: DataStream[String] = env.socketTextStream("localhost", 9999)
//    val studentStream: DataStream[Student] = data.map(new MapFunction[String, Student] {
//      override def map(value: String): Student = {
//        val info: Array[String] = value.split(",")
//        val student = new Student()
//        student.setId(info(0).toInt)
//        student.setName(info(1))
//        student.setAge(info(2).toInt)
//        student
//      }
//    })
//    val url: String = "jdbc:mysql://127.0.0.1:3306/bigdata?serverTimezone=UTC&characterEncoding=UTF-8"
//    val user: String = "root"
//    val password: String = "123456"
//    val sinkToMySQL: RichSinkFunction[String] = new ScalaSinkToMySQL(url, user, password)
//    studentStream.addSink(sinkToMySQL)
//    env.execute("CustomSinkToMySQL")
  }

      case class Student(id: Int, name: String, age: Int)

}
