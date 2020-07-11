package com.white.learning_bigdata.practise.free

object TestCase {
  def main(args: Array[String]): Unit = {
    println(dog("wangcai"))
//    println(dog("wangcai").name)
  }

  // 不用new
  case class dog(name: String)

}
