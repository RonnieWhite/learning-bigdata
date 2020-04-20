package main.java.learning_flink.course08_connectors;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;
import org.apache.flink.util.Collector;

import java.util.Arrays;
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
        FlinkKafkaConsumer<String> sink = new FlinkKafkaConsumer<>(topic, new SimpleStringSchema(), properties);
        /*
         sink.setStartFromEarliest();
         sink.setStartFromLatest();
         long millis = System.currentTimeMillis();
         sink.setStartFromTimestamp(millis);
         sink.setStartFromGroupOffsets();//default
         */
        DataStreamSource<String> data = env.addSource(sink);
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