package com.white.learning_bigdata.practise.class_demo

class Person(var name: String, var age: Int) {
  def this(name: String) {
    this(name, Person.DEFAULT_AGE)
  }

  def this(age: Int) {
    this(Person.DEFAULT_NAME, age)
  }

  def this() {
    this(Person.DEFAULT_NAME)
  }

}

object Person {
  val DEFAULT_NAME = "judy"
  val DEFAULT_AGE = 18

}
