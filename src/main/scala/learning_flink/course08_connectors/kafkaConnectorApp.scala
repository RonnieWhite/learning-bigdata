package learning_flink.course08_connectors

import java.util.Properties

import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}
import org.apache.flink.streaming.connectors.kafka.{FlinkKafkaConsumer, FlinkKafkaProducer, FlinkKafkaProducer011}
import org.apache.flink.api.scala._
import org.apache.flink.streaming.connectors.kafka.internals.KeyedSerializationSchemaWrapper
import org.apache.kafka.clients.consumer.KafkaConsumer

object kafkaConnectorApp {
  def main(args: Array[String]): Unit = {
    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
    //    kafkaConsumer(env)
    kafkaProducer(env)
    env.execute("kafkaConnectorApp")
  }

  def kafkaConsumer(env: StreamExecutionEnvironment): Unit = {
    val topic = "test"
    val properties = new Properties()
    properties.setProperty("bootstrap.servers", "192.168.13.150:9092")
    // Only required for Kafka 0.8
    //    properties.setProperty("zookeeper.connect","192.168.13.150:2181")
    properties.setProperty("group.id", "test")

    val data: DataStream[String] = env.addSource(new FlinkKafkaConsumer[String](topic, new SimpleStringSchema(), properties))
    data.print()
  }

  def kafkaProducer(env: StreamExecutionEnvironment): Unit = {
    val data: DataStream[String] = env.socketTextStream("localhost", 9999)

//    val topic: String = "test"
//    val properties = new Properties()
//    properties.setProperty("bootstrap.servers", "192.168.13.150:9092")
//    val myProducer = new FlinkKafkaProducer[String](topic,
//      new KeyedSerializationSchemaWrapper[String](new SimpleStringSchema()),
//      properties)

    val myProducer = new FlinkKafkaProducer[String](
      "192.168.13.150:9092", // broker list
      "test", // target topic
      new SimpleStringSchema // serialization schema
    )

    // versions 0.10+ allow attaching the records' event timestamp when writing them to Kafka;
    // this method is not available for earlier Kafka versions
    myProducer.setWriteTimestampToKafka(true)
    data.addSink(myProducer)
  }
}
