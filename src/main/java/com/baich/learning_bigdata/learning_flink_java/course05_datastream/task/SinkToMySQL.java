package com.baich.learning_bigdata.learning_flink_java.course05_datastream.task;

import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class SinkToMySQL extends RichSinkFunction<Student> {
    private Connection connection = null;
    private PreparedStatement pstmt = null;
    private String userName = null;
    private String password = null;
    private String driverName = null;
    private String DBUrl = null;

    public SinkToMySQL() {
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

    public void invoke(Student value) throws Exception {

        String sql = "INSERT INTO student(id,name,age)VALUES(?,?,?)";
        pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1, value.getId());
        pstmt.setString(2, value.getName());
        pstmt.setInt(3, value.getAge());
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
