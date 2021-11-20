package com.baich.flink.java.udp.streamconnect;

import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.ConnectedStreams;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.co.CoMapFunction;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;

import java.util.Properties;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-07-07
 * Time : 21:59
 * Description: 消费kafka的两个主题，对获得的两个流进行connect操作
 * Modified By:
 * version : v1.0
 * 主题test1数据格式：xxx,userId,userName,age
 * 主题test2数据格式：userId,xxx,userName,age
 */
public class StreamConnectDemo {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        String topic1 = "test1";
        String topic2 = "test2";
        Properties props = new Properties();
        props.setProperty("bootstrap.servers", "vm01:9092");
        props.setProperty("group.id", "test");
        props.setProperty("auto.offset.reset", "latest");
        props.setProperty("enable.auto.commit", "true");
        props.setProperty("auto.commit.interval.ms", "3000");
        FlinkKafkaConsumer<String> consumer1 = new FlinkKafkaConsumer<String>(topic1, new SimpleStringSchema(), props);
        FlinkKafkaConsumer<String> consumer2 = new FlinkKafkaConsumer<String>(topic2, new SimpleStringSchema(), props);
        DataStreamSource<String> stream1 = env.addSource(consumer1);
        DataStreamSource<String> stream2 = env.addSource(consumer2);
        ConnectedStreams<String, String> connectedStreams = stream1.connect(stream2);
        DataStream<Person> personDataStream = connectedStreams.map(new CoMapFunction<String, String, Person>() {
            @Override
            public Person map1(String s) {
                String[] fields = s.split(",");
                String userId = fields[0];
                String userName = fields[2];
                Integer age = Integer.valueOf(fields[3]);
                return new Person(userId, userName, age);
            }

            @Override
            public Person map2(String s) {
                String[] fields = s.split(",");
                String userId = fields[1];
                String userName = fields[2];
                Integer age = Integer.valueOf(fields[3]);
                return new Person(userId, userName, age);
            }
        });
        personDataStream.print();
        env.execute("StreamConnectDemo");
    }
}
