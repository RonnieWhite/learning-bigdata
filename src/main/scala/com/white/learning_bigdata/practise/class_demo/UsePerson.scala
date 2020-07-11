package com.white.learning_bigdata.practise.class_demo

object UsePerson {
  def main(args: Array[String]): Unit = {
    val person = new Person()
    println(person.name)
    println(person.age)
  }
}
