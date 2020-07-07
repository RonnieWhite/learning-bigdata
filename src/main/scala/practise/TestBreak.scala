package practise

import util.control.Breaks._

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
