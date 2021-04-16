package com.baich.bigdata.practise.free

/**
  * Author: Chenghui Bai
  * Date: 2021/4/15 10:27
  * ProjectName: learning-bigdata
  * PackageName: com.baich.bigdata.practise.free
  * ClassName: DefaultParamDemo
  * Version:
  * Description:
  */
object DefaultParamDemo {
  def main(args: Array[String]): Unit = {
    val demo = new DefaultParamDemo
    val res = demo.add(1)
    println(res)
  }
}

class DefaultParamDemo {
  def add(a: Int, b: Int = 3): Int = {
    a + b
  }
}
