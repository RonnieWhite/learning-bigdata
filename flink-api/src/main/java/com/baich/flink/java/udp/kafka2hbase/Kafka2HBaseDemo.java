package com.baich.flink.java.udp.kafka2hbase;

import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;

import java.util.Properties;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-07-09
 * Time : 10:32
 * Description: 消费kafka主题，并将数据存入HBase对应表中
 * Modified By:
 * version : v1.0
 * kafka 主题数据类型：1,2020-06-01 00:01:02,2,69,45
 * create 'test','way'
 */
public class Kafka2HBaseDemo {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        // kafka source
        String topic = "test";
        Properties props = new Properties();
        props.setProperty("bootstrap.servers", "vm01:9092");
        props.setProperty("group.id", "test");
        props.setProperty("auto.offset.reset", "latest");
        props.setProperty("enable.auto.commit", "true");
        props.setProperty("auto.commit.interval.ms", "3000");
        FlinkKafkaConsumer<String> consumer = new FlinkKafkaConsumer<String>(topic, new SimpleStringSchema(), props);
        DataStreamSource<String> stream = env.addSource(consumer);
        stream.addSink(new HBaseWriter());
        env.execute("Kafka2HBaseDemo");
    }
}
