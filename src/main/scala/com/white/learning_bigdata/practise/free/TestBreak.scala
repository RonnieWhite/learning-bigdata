package com.white.learning_bigdata.practise.free

import scala.util.control.Breaks.{break, breakable}

object TestBreak {
  def main(args: Array[String]): Unit = {
    breakable(
      for (i <- 1 to 10) {
        println(i)
        if (i > 4) break
      }
    )
  }
}
