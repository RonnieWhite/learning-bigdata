package com.baich.flink.java.udp.kafka2hdfs;

import org.apache.flink.api.common.serialization.SimpleStringEncoder;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.connector.kafka.source.KafkaSource;
import org.apache.flink.core.fs.Path;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.sink.filesystem.StreamingFileSink;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;

import java.util.Properties;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-07-09
 * Time : 11:43
 * Description:
 * Modified By:
 * version : v1.0
 */
public class KafkaHdfsDemo {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        String topic = "test1";
        Properties props = new Properties();
        props.setProperty("bootstrap.servers", "vm01:9092");
        props.setProperty("group.id", "test");
        props.setProperty("auto.offset.reset", "latest");
        props.setProperty("enable.auto.commit", "true");
//        props.setProperty("auto.commit.interval.ms", "3000");
        FlinkKafkaConsumer<String> consumer = new FlinkKafkaConsumer<String>(topic, new SimpleStringSchema(), props);
        DataStreamSource<String> stream = env.addSource(consumer);

        StreamingFileSink<String> sink = StreamingFileSink.<String>forRowFormat(new Path("hdfs://vm01:9000/tmp/test"), new SimpleStringEncoder<>("UTF-8")).build();
        stream.addSink(sink);
        env.execute("KafkaHdfsDemo");

    }
}
