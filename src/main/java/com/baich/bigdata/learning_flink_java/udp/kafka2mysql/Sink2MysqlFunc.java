package com.baich.bigdata.learning_flink_java.udp.kafka2mysql;

import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Timestamp;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-07-08
 * Time : 20:09
 * Description: kafka2mysql SinkFunction
 * Modified By:
 * version : v1.0
 */
public class Sink2MysqlFunc extends RichSinkFunction<Tuple2<String, Long>> {
    private Connection connection = null;
    private PreparedStatement pstmt = null;
    private String userName = null;
    private String password = null;
    private String driverName = null;
    private String DBUrl = null;

    public Sink2MysqlFunc() {
        userName = "root";
        password = "123456";
        driverName = "com.mysql.jdbc.Driver";
        DBUrl = "jdbc:mysql://vm01:3306/kafka?serverTimezone=UTC&characterEncoding=UTF-8";
    }

    @Override
    public void open(Configuration parameters) throws Exception {
        if (connection == null) {
            Class.forName(driverName);
            connection = DriverManager.getConnection(DBUrl, userName, password);
        }
    }

    @Override
    public void invoke(Tuple2<String, Long> value, Context context) throws Exception {
        String sql = "INSERT INTO kafka_sink(word,wc,time_mark)VALUES(?,?,?)";
        pstmt = connection.prepareStatement(sql);
        Timestamp now = new Timestamp(System.currentTimeMillis());
        pstmt.setString(1, value.f0);
        pstmt.setLong(2, value.f1);
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

