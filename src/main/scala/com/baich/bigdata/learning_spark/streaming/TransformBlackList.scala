package com.baich.bigdata.learning_spark.streaming

import java.lang

import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}

object TransformBlackList {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setAppName("tbs").setMaster("local[2]")
    val sc: StreamingContext = new StreamingContext(conf, Seconds(5))
    // 创建黑名单
    val blacklist: Array[(String, Boolean)] = Array(("tom", true))
    val blacklistRDD: RDD[(String, Boolean)] = sc.sparkContext.parallelize(blacklist, 5)
    val userAdsClickLogStream: DStream[(String, String)] = sc.socketTextStream("localhost", 9999)
      .map(s => (s.split(" ")(1), s))
    val validAdsClickLogStream: DStream[(String, (String, Option[Boolean]))] = userAdsClickLogStream.transform(userAdsClickLogStream => {
      val joinRDD: RDD[(String, (String, Option[Boolean]))] = userAdsClickLogStream.leftOuterJoin(blacklistRDD)
      joinRDD.filter(tuple => {
        if (tuple._2._2.getOrElse(false)) {
          false
        } else {
          true
        }
      })
    })
    validAdsClickLogStream.map(_._2._1).print()
    sc.start()
    sc.awaitTermination()
    sc.stop()
  }
}
