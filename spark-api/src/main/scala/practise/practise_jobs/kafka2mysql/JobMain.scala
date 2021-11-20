package practise.practise_jobs.kafka2mysql

import java.lang

import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.SparkConf
import org.apache.spark.streaming.kafka010.{ConsumerStrategies, KafkaUtils, LocationStrategies}
import org.apache.spark.streaming.{Seconds, StreamingContext}

object JobMain {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[2]").setAppName("kafka2mysql").set("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
    val ssc = new StreamingContext(conf, Seconds(5))
    val kafkaParams = Map[String, Object](
      "group.id" -> "DefaultConsumerGroup",
      "bootstrap.servers" -> "vm01:9092",
      "key.deserializer" -> classOf[StringDeserializer],
      "value.deserializer" -> classOf[StringDeserializer],
      "auto.offset.reset" -> "latest",
      "enable.auto.commit" -> (false: lang.Boolean)
    )
    val data = KafkaUtils.createDirectStream(ssc, LocationStrategies.PreferConsistent, ConsumerStrategies.Subscribe[String, String](Array("test"), kafkaParams))

    val finalRDD = data.transform(rdd => {
      if (!rdd.isEmpty()) {
        val tuples = rdd.map(_.value())
          .flatMap(_.toString.split(" "))
          .map((_, 1))
          .reduceByKey(_ + _)
          .map(tuple => (tuple._2, tuple._1))
          .sortByKey(false: Boolean)
          .map(tuple => (tuple._2, tuple._1))
          .take(5)
        val recordArray = new Array[Record](5)
        var i = 0
        for (tuple <- tuples) {
          recordArray(i) = Record(tuple._1, tuple._2)
          i += 1
        }
        println("=====")
        for (record <- recordArray) {
          println(record)
        }
        val dBConnector = new DBConnector("localhost", "root", "123456", "abc", recordArray)
        dBConnector.insertData()
        println("Ok...")
      }
      rdd
    })
    finalRDD.print()
    ssc.start()
    ssc.awaitTermination()
    ssc.stop()
  }

}
