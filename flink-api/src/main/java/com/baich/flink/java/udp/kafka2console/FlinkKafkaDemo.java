package com.baich.flink.java.udp.kafka2console;

import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;

import java.util.Properties;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-07-07
 * Time : 20:05
 * Description: 消费Kafka主题，并打印到控制台
 * Modified By:
 * version : v1.0
 */
public class FlinkKafkaDemo {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
//        env.setStreamTimeCharacteristic(TimeCharacteristic.ProcessingTime);
        String topic = "test";
        Properties props = new Properties();
        props.setProperty("bootstrap.servers", "vm01:9092");
        props.setProperty("group.id", "test");
        props.setProperty("auto.offset.reset", "latest"); // latest/earliest
        props.setProperty("enable.auto.commit", "true");
        props.setProperty("auto.commit.interval.ms", "3000");
        FlinkKafkaConsumer<String> consumer = new FlinkKafkaConsumer<String>(topic, new SimpleStringSchema(), props);
        DataStreamSource<String> stream = env.addSource(consumer);
        stream.print();
        env.execute("FlinkKafkaDemo");
    }
}
