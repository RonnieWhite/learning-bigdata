package com.white.learning_bigdata.learning_spark.streaming

import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.InputDStream
import org.apache.spark.streaming.kafka010.{ConsumerStrategies, KafkaUtils, LocationStrategies}
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  * 一个kafka消费者
  */
object KafkaConsumer {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setAppName("ckf").setMaster("local[2]")
    val sc: StreamingContext = new StreamingContext(conf, Seconds(5))
    val kafkaParams: Map[String, Object] = Map[String, Object](
      "group.id" -> "DefaultConsumerGroup",
      "bootstrap.servers" -> "myspark:9092",
      "key.deserializer" -> classOf[StringDeserializer],
      "value.deserializer" -> classOf[StringDeserializer],
      "auto.offset.reset" -> "latest",
      "enable.auto.commit" -> (false: java.lang.Boolean)
    )
    val topics: String = "test"
    val topicSet: Array[String] = Array(topics)
    val data: InputDStream[ConsumerRecord[String, String]] = KafkaUtils.createDirectStream(sc,
      LocationStrategies.PreferConsistent,
      ConsumerStrategies.Subscribe[String, String](topicSet, kafkaParams))
    data.foreachRDD(_.foreach(x => println(x.value())))
    sc.start()
    sc.awaitTermination()
    sc.stop()
  }
}
