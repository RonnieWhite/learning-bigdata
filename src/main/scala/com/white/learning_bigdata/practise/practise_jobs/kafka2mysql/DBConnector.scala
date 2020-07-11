package com.white.learning_bigdata.practise.practise_jobs.kafka2mysql

import java.sql.{Timestamp, DriverManager, Connection}

class DBConnector(ip: String, username: String, password: String, database: String, recordArray: Array[Record]) {
  val url = s"jdbc:mysql://$ip:3306/$database?useUnicode=true&characterEncoding=utf-8&useSSL=false"
  val driver = "com.mysql.jdbc.Driver"

  def insertData(): Unit = {
    var connection: Connection = null
    try {
      Class.forName(driver)
      connection = DriverManager.getConnection(url, username, password)
      val sql = "INSERT INTO t_realtime_ranking(coll_time,name,times,ranking)VALUES(?,?,?,?)"
      val pstmt = connection.prepareStatement(sql)
      val coll_time = new Timestamp(new java.util.Date().getTime)
      var i = 1
      for (record <- recordArray) {
        pstmt.setTimestamp(1, coll_time)
        pstmt.setString(2, record.name)
        pstmt.setInt(3, record.times)
        pstmt.setInt(4, i)
        pstmt.addBatch()
        i += 1
      }
      pstmt.executeBatch()
      pstmt.clearBatch()
    } catch {
      case e: Exception => e.printStackTrace()
    }
    connection.close()
  }

}

case class Record(name: String, times: Int)
