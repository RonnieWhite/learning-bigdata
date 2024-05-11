package com.baich.flink.java.kafka;

import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;

import java.util.Properties;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2024-05-11
 * Time : 18:18
 * Description:
 * Modified By:
 * version : v1.0
 */
public class FlinkKafkaReader {
    public static void main(String[] args) throws Exception {
        Properties props = new Properties();
        props.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "vm01:9092");
        props.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "testGroup");
        FlinkKafkaConsumer<String> consumer = new FlinkKafkaConsumer<>("test01", new SimpleStringSchema(), props);
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
//        consumer.setStartFromEarliest();
//        consumer.setStartFromLatest();
        // 按时间戳进行消费
        consumer.setStartFromTimestamp(17154L);
        env.addSource(consumer).print();
        env.execute();
    }
}
