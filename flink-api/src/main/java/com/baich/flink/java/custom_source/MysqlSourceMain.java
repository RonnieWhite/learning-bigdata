package com.baich.flink.java.custom_source;

import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2020-09-23
 * Time : 14:16
 * Description:
 * Modified By:
 * version : v1.0
 */
public class MysqlSourceMain {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        String query = "SELECT id,title FROM t_blog";
//        DataStreamSource<MysqlBlog> data = env.addSource(new MysqlSourceFunction(query));
        DataStreamSource<MysqlBlog> data = env.addSource(new MysqlParallelSourceFunction(query));
        data.print();
//                .setParallelism(1);
        env.execute("MySQL-Test");
    }
}
