package com.baich.flink.java.udp.kafka2kafka;

import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;

import java.util.Properties;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-07-09
 * Time : 08:56
 * Description:
 * Modified By:
 * version : v1.0
 */
public class Kafka2KafkaDemo {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        String consumerTopic = "test1";
        String producerTopic = "test2";
        Properties props = new Properties();
        props.setProperty("bootstrap.servers", "vm01:9092");
        props.setProperty("group.id", "test");
        props.setProperty("auto.offset.reset", "latest"); // latest/earliest
        props.setProperty("enable.auto.commit", "true");
        props.setProperty("auto.commit.interval.ms", "3000");
        FlinkKafkaConsumer<String> consumer = new FlinkKafkaConsumer<String>(consumerTopic, new SimpleStringSchema(), props);
        DataStreamSource<String> stream = env.addSource(consumer);
        FlinkKafkaProducer<String> producer = new FlinkKafkaProducer<>(producerTopic, new SimpleStringSchema(), props);
        stream.addSink(producer);
        env.execute("Kafka2KafkaDemo");
    }

}
