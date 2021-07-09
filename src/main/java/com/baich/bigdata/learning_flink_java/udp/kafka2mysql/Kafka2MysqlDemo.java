package com.baich.bigdata.learning_flink_java.udp.kafka2mysql;

import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;

import java.util.Properties;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-07-07
 * Time : 20:05
 * Description:消费kafka主题，WordCount之后将数据与时间存入MySQL
 * Modified By:
 * version : v1.0
 * kafka主题数据样式：python,python,python,java,java,scala
 * MySQL对应表建表语句：CREATE TABLE kafka_sink(word VARCHAR(255),wc BIGINT,time_mark TIMESTAMP);
 */
public class Kafka2MysqlDemo {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStreamSource<String> source = env.addSource(getKafkaConsumer());
        SingleOutputStreamOperator<Tuple2<String, Long>> sums = source.flatMap(new SplitterFunc())
                .keyBy(0)
                .timeWindow(Time.seconds(10))
                .sum(1);
        sums.addSink(new Sink2MysqlFunc());
        env.execute("Kafka2Mysql");
    }

    private static FlinkKafkaConsumer<String> getKafkaConsumer() {
        String topic = "test";
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "vm01:9092");
        properties.setProperty("group.id", "test");
        return new FlinkKafkaConsumer<>(topic, new SimpleStringSchema(), properties);
    }
}
