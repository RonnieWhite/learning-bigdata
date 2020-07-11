package com.white.learning_bigdata.learning_flink.course05_datastream.task

import java.sql.{Connection, DriverManager}

import com.google.gson.Gson
import CustomSinkToMySQL.Student
import org.apache.flink.configuration.Configuration
import org.apache.flink.streaming.api.functions.sink.{RichSinkFunction, SinkFunction}

class ScalaSinkToMySQL(url: String, user: String, pwd: String) extends RichSinkFunction[String] {

  var conn: Connection = _

  override def open(parameters: Configuration): Unit = {
    super.open(parameters)
    Class.forName("com.mysql.cj.jdbc.Driver")
    conn = DriverManager.getConnection(url, user, pwd)
    conn.setAutoCommit(false)
  }

  override def invoke(value: String, context: SinkFunction.Context[_]): Unit = {
    super.invoke(value, context)
    val g = new Gson()
    val s = g.fromJson(value, classOf[Student])
    val pstmt = conn.prepareStatement("INSERT INTO student(id,name,age)VALUES(?,?,?)")
    pstmt.setInt(1, s.id)
    pstmt.setString(2, s.name)
    pstmt.setInt(3, s.age)
    pstmt.executeUpdate()
  }

  override def close(): Unit = {
    super.close()
    conn.close()
  }
}