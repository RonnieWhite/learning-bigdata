package com.baich.bigdata.learning_flink_java.udp.kafka2redis;

import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.streaming.connectors.redis.RedisSink;
import org.apache.flink.streaming.connectors.redis.common.config.FlinkJedisPoolConfig;

import java.util.Properties;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-07-08
 * Time : 20:14
 * Description: 消费kafka主题，将数据下沉到redis
 * Modified By:
 * version : v1.0
 * 主题test数据格式：key,value
 */
public class Kafka2RedisDemo {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
//        消费kafka
        String topic = "test";
        Properties props = new Properties();
        props.setProperty("bootstrap.servers", "vm01:9092");
        props.setProperty("group.id", "test");
        props.setProperty("auto.offset.reset", "latest"); // latest/earliest
        props.setProperty("enable.auto.commit", "true");
        props.setProperty("auto.commit.interval.ms", "3000");
        FlinkKafkaConsumer<String> consumer = new FlinkKafkaConsumer<String>(topic, new SimpleStringSchema(), props);
        DataStreamSource<String> stream = env.addSource(consumer);
// 下沉到redis
        FlinkJedisPoolConfig config = new FlinkJedisPoolConfig.Builder()
                .setHost("localhost")
                .setPort(6379)
                .build();
        stream.addSink(new RedisSink<>(config, new MyRedisMapper()));
        env.execute("FlinkKafkaRedisDemo");
    }
}
