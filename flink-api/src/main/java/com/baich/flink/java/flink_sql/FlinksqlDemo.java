package com.baich.flink.java.flink_sql;

import com.baich.flink.java.watermark.Order;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.ProcessFunction;
import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.TableResult;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;
import org.apache.flink.types.Row;
import org.apache.flink.util.CloseableIterator;
import org.apache.flink.util.Collector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-12-02
 * Time : 09:34
 * Description:
 * Modified By:
 * version : v1.0
 */
public class FlinksqlDemo {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        EnvironmentSettings settings = EnvironmentSettings.newInstance().useBlinkPlanner().inStreamingMode().build();
        StreamTableEnvironment tableEnv = StreamTableEnvironment.create(env, settings);

        SingleOutputStreamOperator<Order> orderStream = env.socketTextStream("192.168.232.130", 9999)
                .process(new ProcessFunction<String, Order>() {
                    @Override
                    public void processElement(String s, ProcessFunction<String, Order>.Context context, Collector<Order> collector) throws Exception {
                        String[] fields = s.split(",");
                        if (fields.length < 3) return;
                        collector.collect(new Order(fields[0], Integer.parseInt(fields[1]), Long.parseLong(fields[2])));
                    }
                });
        Table table = tableEnv.fromDataStream(orderStream);
        tableEnv.createTemporaryView("t_order", table);
        TableResult tableResult = tableEnv.executeSql("select orderId,sum(money) as total from t_order group by orderId");
        CloseableIterator<Row> collect = tableResult.collect();
        while (collect.hasNext()) {
            String orderId = (String) collect.next().getField("orderId");
            int total = (Integer) collect.next().getField("total");
            insertData(orderId, total);
        }
        env.execute("FlinksqlDemo");
    }


    private static final String JDBC_URL = "jdbc:mysql://192.168.232.130:3306/ods?characterEncoding=UTF-8&useSSL=false";
    private static Connection connection;
    private static PreparedStatement pstmt;

    private static void open() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection(JDBC_URL, "root", "123456");

    }

    private static void insertData(String orderid, int total) throws Exception {
        open();
        String sql = "insert into t_order(order_id,total) values (?,?)";
        pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, orderid);
        pstmt.setInt(2, total);
        pstmt.executeUpdate();
        close();
    }

    private static void close() throws SQLException {
        if (pstmt != null) {
            pstmt.close();
        }
        if (connection != null) {
            connection.close();
        }
    }
}
