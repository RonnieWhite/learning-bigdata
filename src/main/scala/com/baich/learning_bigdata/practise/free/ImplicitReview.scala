package com.baich.learning_bigdata.practise.free

import java.util.Date

object ImplicitReview {

  class MyDate(val date: Date) {
    def printTime(): Unit = {
      print(date)
      print(date.getTime)
    }
  }

  object MyDate {
    implicit def printT(date: Date) = new MyDate(date)
  }

  def main(args: Array[String]): Unit = {
    import MyDate._
    new Date().printTime()
  }


}
