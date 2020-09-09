package com.baich.learning_bigdata.practise.free

import java.sql.Timestamp

object test1 {
  def main(args: Array[String]): Unit = {
    val date = new Timestamp(new java.util.Date().getTime)
    print(date)
  }

}
