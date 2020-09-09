package com.baich.bigdata.practise.free

import scala.util.matching.Regex

object TestRegex {
  def main(args: Array[String]): Unit = {
    val str = "Life is short 999!"
    val regex = new Regex("[1-9]+")
    val maybeString = regex.findFirstIn(str)
    println(maybeString)
  }
}
