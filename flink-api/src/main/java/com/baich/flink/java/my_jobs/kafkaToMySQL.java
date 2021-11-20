package com.baich.flink.java.my_jobs;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.util.Collector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.Properties;

public class kafkaToMySQL {
    public static void main(String[] args) throws Exception {
        System.setProperty("HADOOP_USER_NAME", "sparkuser");
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStreamSource<String> source = env.addSource(getKafkaConsumer());
        SingleOutputStreamOperator<Tuple2<String, Integer>> sums = source.flatMap(new splitterFunc())
                .keyBy(0)
                .timeWindow(Time.seconds(10))
                .sum(1);
        sums.addSink(new sinkToMySQLFunc());
        env.execute("kafkaToMySQL");

    }

    private static FlinkKafkaConsumer<String> getKafkaConsumer() {
        String topic = "test";
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "myspark:9092");
        properties.setProperty("group.id", "test");
        return new FlinkKafkaConsumer<>(topic, new SimpleStringSchema(), properties);
    }

    static class splitterFunc implements FlatMapFunction<String, Tuple2<String, Integer>> {
        @Override
        public void flatMap(String value, Collector<Tuple2<String, Integer>> out) throws Exception {
            String[] words = value.toLowerCase().split(" ");
            for (String word : words) {
                out.collect(new Tuple2<>(word, 1));
            }
        }
    }

    static class sinkToMySQLFunc extends RichSinkFunction<Tuple2<String, Integer>> {
        private Connection connection = null;
        private PreparedStatement pstmt = null;
        private String userName = null;
        private String password = null;
        private String driverName = null;
        private String DBUrl = null;

        public sinkToMySQLFunc() {
            userName = "root";
            password = "123456";
            driverName = "com.mysql.cj.jdbc.Driver";
            DBUrl = "jdbc:mysql://127.0.0.1:3306/bigdata?serverTimezone=UTC&characterEncoding=UTF-8";
        }

        @Override
        public void open(Configuration parameters) throws Exception {
            if (connection == null) {
                Class.forName(driverName);
                connection = DriverManager.getConnection(DBUrl, userName, password);
            }
        }

        public void invoke(Tuple2<String, Integer> value) throws Exception {
            String sql = "INSERT INTO kafka_sink(word,wc,time_mark)VALUES(?,?,?)";
            pstmt = connection.prepareStatement(sql);
            Timestamp now = new Timestamp(new java.util.Date().getTime());
            pstmt.setString(1, value.f0);
            pstmt.setInt(2, value.f1);
            pstmt.setTimestamp(3, now);
            pstmt.executeUpdate();
        }

        @Override
        public void close() throws Exception {
            if (pstmt != null) {
                pstmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
}
