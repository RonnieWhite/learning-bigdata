package com.baich.flink.java.custom_source;

import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.source.RichParallelSourceFunction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2020-09-23
 * Time : 16:33
 * Description:
 * Modified By:
 * version : v1.0
 */
public class MysqlParallelSourceFunction extends RichParallelSourceFunction<MysqlBlog> {
    private Connection connection;
    private String query;
    private PreparedStatement pstm;

    MysqlParallelSourceFunction(String query) {
        this.query = query;
    }

    @Override
    public void open(Configuration parameters) throws Exception {
        super.open(parameters);
        connection = getConn();
        pstm = connection.prepareStatement(query);
    }

    private Connection getConn() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/abc", "root", "Bai@529381");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;

    }

    @Override
    public void run(SourceContext<MysqlBlog> ctx) throws Exception {
        ResultSet resultSet = pstm.executeQuery();
        synchronized (ctx.getCheckpointLock()) {
            while (resultSet.next()) {
                MysqlBlog blog = new MysqlBlog(resultSet.getLong(1), resultSet.getString(2));
                ctx.collect(blog);
            }
        }
    }

    @Override
    public void cancel() {
    }

    @Override
    public void close() throws Exception {
        super.close();
        if (connection != null) {
            connection.close();
        }
        if (pstm != null) {
            pstm.close();
        }
    }
}
