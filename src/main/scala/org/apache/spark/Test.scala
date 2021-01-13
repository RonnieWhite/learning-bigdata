package org.apache.spark

import org.apache.spark.util.Utils

/**
  * Author: Chenghui Bai
  * Date: 2021/1/12 10:02
  * ProjectName: learning-bigdata
  * PackageName: org.apache.spark.util
  * ClassName: Test
  * Version:
  * Description:
  */

//import org.apache.spark.util._

object Test {
  def main(args: Array[String]): Unit = {
    xxx()
    val site = Utils.getCallSite()
    println(site)
  }

  def xxx(): Unit = {
    val site = Utils.getCallSite()
    println(site)
    println("===========")

  }
}
