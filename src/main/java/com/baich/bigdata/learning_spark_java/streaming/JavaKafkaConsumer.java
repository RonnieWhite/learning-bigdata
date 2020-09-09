package com.baich.bigdata.learning_spark_java.streaming;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.spark.SparkConf;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
//import org.apache.spark.streaming.kafka.KafkaUtils;
import org.apache.spark.streaming.kafka010.*;
import org.apache.kafka.common.serialization.StringDeserializer;
import scala.Tuple2;

import java.util.*;

/**
 * 创建kafka消费者
 */
public class JavaKafkaConsumer {
    public static void main(String[] args) throws Exception {
        SparkConf conf = new SparkConf().setAppName("kafkaDemo").setMaster("local[2]");
        JavaStreamingContext jsc = new JavaStreamingContext(conf, Durations.seconds(5));
        /**
         HashMap<String, Integer> topicThreadMap = new HashMap<>();
         topicThreadMap.put("test", 1);
         // 创建kafka的输入数据流
         JavaPairReceiverInputDStream<String, String> line = KafkaUtils.createStream(
         jsc,
         "myspark:2181",
         "DefaultConsumerGroup",
         topicThreadMap
         );
         */
        Map<String, Object> kafkaParams = new HashMap<>();
        kafkaParams.put("group.id", "DefaultConsumerGroup");
        kafkaParams.put("bootstrap.servers", "myspark:9092");
        kafkaParams.put("key.deserializer", StringDeserializer.class);
        kafkaParams.put("value.deserializer", StringDeserializer.class);
        kafkaParams.put("auto.offset.reset", "latest");
        kafkaParams.put("enable.auto.commit", false);
//        String[] topics = new String[]{"test"};
        String topics = "test";
        List<String> topicSet = Arrays.asList(topics.split(","));
//        HashMap<TopicPartition, Long> offsets = new HashMap<>();
//        offsets.put(new TopicPartition("test", 1), 1L);
        //如果true,consumer定期地往zookeeper写入每个分区的offset
        JavaInputDStream<ConsumerRecord<Object, Object>> stream = KafkaUtils.createDirectStream(
                jsc,
                LocationStrategies.PreferConsistent(),
//                ConsumerStrategies.Subscribe(topicSet, kafkaParams, offsets)
                ConsumerStrategies.Subscribe(topicSet, kafkaParams)
        );
        stream.flatMap(line -> Arrays.asList(line.value().toString().split(" ")).iterator())
                .mapToPair(word -> new Tuple2<String, Integer>(word, 1))
                .reduceByKey((i1, i2) -> i1 + i2)
                .print();
        jsc.start();
        jsc.awaitTermination();
        jsc.close();
    }
}
