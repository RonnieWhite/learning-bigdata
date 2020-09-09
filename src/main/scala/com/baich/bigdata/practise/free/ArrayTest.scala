package com.baich.bigdata.practise.free

object ArrayTest {
  def main(args: Array[String]): Unit = {
    val fruits = Array("banana", "orange", "apple")
    //    forDemo1(fruits)
    //    forDemo2(fruits)
    //    foreachDemo(fruits)
    forIfDemo(fruits)
  }

  def forDemo1(fruits: Array[String]): Unit = {
    for (fruit <- fruits) {
      val upperCase = fruit.toUpperCase
      println(upperCase)
    }
  }

  def forDemo2(fruits: Array[String]): Unit = {
    for (fruit <- fruits) yield {
      val lowerCase = fruit.toLowerCase
      println(lowerCase)
    }
  }

  def forIfDemo(fruits: Array[String]): Unit = {
    for (fruit <- fruits if fruit != "banana") println(fruit)
  }

  def foreachDemo(fruits: Array[String]): Unit = {
    fruits.foreach(e => {
      val upperCase = e.toUpperCase
      println(upperCase)
    })
  }
}
