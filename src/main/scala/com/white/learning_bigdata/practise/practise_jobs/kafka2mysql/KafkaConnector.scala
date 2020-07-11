package com.white.learning_bigdata.practise.practise_jobs.kafka2mysql

import java.lang

import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.dstream.InputDStream
import org.apache.spark.streaming.kafka010.{ConsumerStrategies, KafkaUtils, LocationStrategies}

class KafkaConnector(ssc: StreamingContext, ip: String, topics: Array[String]) {
  def consumer(): InputDStream[ConsumerRecord[String, String]] = {
    val kafkaParams = Map[String, Object](
      "group.id" -> "DefaultConsumerGroup",
      "bootstrap.servers" -> s"$ip:9092",
      "key.deserializer" -> classOf[StringDeserializer],
      "value.deserializer" -> classOf[StringDeserializer],
      "auto.offset.reset" -> "latest",
      "enable.auto.commit" -> (false: lang.Boolean)
    )
    KafkaUtils.createDirectStream(ssc, LocationStrategies.PreferConsistent, ConsumerStrategies.Subscribe[String, String](topics, kafkaParams))
  }


}
