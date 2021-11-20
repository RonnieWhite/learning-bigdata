package com.baich.flink.java.custom_source;

import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.source.RichSourceFunction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2020-09-23
 * Time : 15:18
 * Description:
 * Modified By:
 * version : v1.0
 */
public class MysqlSourceFunction extends RichSourceFunction<MysqlBlog> {
    private String query;
    private Connection conn;
    private PreparedStatement pstmt;

    MysqlSourceFunction(String query) {
        this.query = query;
    }

    @Override
    public void open(Configuration parameters) throws Exception {
        super.open(parameters);
        Connection conn = getConn();
        pstmt = conn.prepareStatement(query);
    }

    private Connection getConn() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/abc", "root", "Bai@529381");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    @Override
    public void run(SourceContext<MysqlBlog> ctx) throws Exception {
        ResultSet resultSet = pstmt.executeQuery();
        while (resultSet.next()) {
            MysqlBlog blog = new MysqlBlog(resultSet.getLong(1), resultSet.getString(2));
            ctx.collect(blog);
        }
    }

    @Override
    public void cancel() {
    }
}
