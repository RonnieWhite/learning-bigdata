package com.baich.bigdata.practise.companion_demo

/**
  * Author: Chenghui Bai
  * Date: 2021/1/4 16:04
  * ProjectName: learning-bigdata
  * PackageName: com.baich.bigdata.practise.companion_demo
  * ClassName: CompanionDemo
  * Version:
  * Description:
  */
class CompanionDemo {


}

object CompanionDemo {
  def apply: CompanionDemo = {
    println("who do you think we are.")
    new CompanionDemo()
  }

  def main(args: Array[String]): Unit = {
    val demo = CompanionDemo.apply
    print("ok")
  }
}