package com.baich.flink.java;

import org.apache.flink.api.common.functions.RichMapFunction;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.ProcessFunction;
import org.apache.flink.util.Collector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-12-02
 * Time : 15:48
 * Description:
 * Modified By:
 * version : v1.0
 */
public class FlinkTest {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStreamSource<String> stream = env.socketTextStream("192.168.232.130", 9999);
        stream.map(new RichMapFunction<String, String>() {
                    @Override
                    public String map(String s) throws Exception {
                        System.out.println(Thread.currentThread().getName() + " map");
                        return s;
                    }
                })
                .process(new MyProcessFunction()).setParallelism(1).map(new RichMapFunction<String, String>() {
                    @Override
                    public String map(String s) throws Exception {
                        System.out.println(Thread.currentThread().getName() + " secondMap");
                        return s;
                    }
                }).print();

        env.execute();
    }

    static class MyProcessFunction extends ProcessFunction<String, String> {

        @Override
        public void open(Configuration parameters) throws Exception {
            super.open(parameters);
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://192.168.232.130:3306/ods?characterEncoding=UTF-8&useSSL=false", "root", "123456");
            PreparedStatement pstmt = connection.prepareStatement("select * from t_order");
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                String concat = resultSet.getString(1).concat(resultSet.getString(2));
                System.out.println("====" + concat + "========");
            }
        }

        @Override
        public void processElement(String s, ProcessFunction<String, String>.Context context, Collector<String> collector) throws Exception {
            collector.collect(s);
        }
    }


}
