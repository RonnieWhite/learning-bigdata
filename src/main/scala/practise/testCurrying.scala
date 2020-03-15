package main.scala.practise

object testCurrying {
  def main(args: Array[String]): Unit = {
    println(runCurrying(1)(2))
  }

  def runCurrying(a: Int)(b: Int): Int = {
    a + b
  }

}
