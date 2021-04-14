package com.baich.bigdata.learning_spark.spark_sql


import org.apache.spark.SparkConf
import org.apache.spark.sql.{Row, SparkSession}

/**
  * Author: Chenghui Bai
  * Date: 2021/4/9 16:35
  * ProjectName: learning-bigdata
  * PackageName: com.baich.bigdata.learning_spark.spark_sql
  * ClassName: Spark2Hive
  * Version:
  * Description:
  */
object Spark2Hive {
  def main(args: Array[String]): Unit = {
    System.setProperty("HADOOP_USER_NAME", "root")
    val conf = new SparkConf()
      .setAppName("Spark2Hive")
      .setMaster("local")
      .set("hive.exec.dynamic.partition.mode", "nonstrict")
      .set("hive.metastore.uris", "thrift://vm01:9083")
      .set("fs.defaultFS", "hdfs://vm01:9000")
    val spark = SparkSession.builder().config(conf).enableHiveSupport().getOrCreate()
    //    val spark = SparkSession.builder().config(conf).getOrCreate()
    //    val schema = new StructType(
    //      Array(
    //        StructField("playerName", StringType),
    //        StructField("num", StringType),
    //        StructField("team", StringType)
    //      ))


    //    val value = spark.read.text("file:///E:/data/data.txt").as[PlayerInfo]
    import spark.implicits._
    val playerInfo = spark.sparkContext.textFile("file:///E:/data/data.txt")
      .map(_.split(","))
      .map(array => PlayerInfo(array(0), new Integer(array(1)), array(2)))
      .toDF()
    playerInfo.show()
    playerInfo.write.mode("append").format("orc").insertInto("ods.player_info")

    //      .rdd
    //    val printRdd = rowRdd.map(row => row.toString()).flatMap(line => line.split(",")).map(word => (word, 1)).reduceByKey(_ + _)
    //    printRdd.foreach(println)

    spark.stop()
  }

  case class PlayerInfo(playerName: String, num: Integer, team: String)

}
