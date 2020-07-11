package com.white.learning_bigdata.practise.free

object TestCurrying {
  def main(args: Array[String]): Unit = {
    println(runCurrying(1)(2))
  }

  def runCurrying(a: Int)(b: Int): Int = {
    a + b
  }

}
