package com.baich.bigdata.learning_flink_java.course08_connectors;

import org.apache.commons.collections.map.HashedMap;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;
import org.apache.flink.streaming.connectors.kafka.internals.KafkaTopicPartition;

import java.util.Map;
import java.util.Properties;

public class JavakafkaConnectorApp {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
//        kafkaConsumer(env);
        kafkaProducer(env);
        env.execute("JavakafkaConnectorApp");
    }

    private static void kafkaConsumer(StreamExecutionEnvironment env) {
        String topic = "test";
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "myspark:9092");
        properties.setProperty("group.id", "test");
        FlinkKafkaConsumer<String> consumer = new FlinkKafkaConsumer<>(topic, new SimpleStringSchema(), properties);

        Map<KafkaTopicPartition, Long> offsets = new HashedMap();
        offsets.put(new KafkaTopicPartition("test1", 0), 11L);
        offsets.put(new KafkaTopicPartition("test1", 1), 22L);
        offsets.put(new KafkaTopicPartition("test1", 2), 3L);

        consumer.setStartFromSpecificOffsets(offsets);
        /*
         sink.setStartFromEarliest();
         sink.setStartFromLatest();
         long millis = System.currentTimeMillis();
         sink.setStartFromTimestamp(millis);
         sink.setStartFromGroupOffsets();//default
         */
        DataStreamSource<String> data = env.addSource(consumer);
        data.print();
    }

    private static void kafkaProducer(StreamExecutionEnvironment env) {
        DataStreamSource<String> data = env.socketTextStream("localhost", 9999);
        String topic = "test";
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "myspark:9092");
        FlinkKafkaProducer<String> producer = new FlinkKafkaProducer<>(topic, new SimpleStringSchema(), properties);
        data.addSink(producer);
    }
}