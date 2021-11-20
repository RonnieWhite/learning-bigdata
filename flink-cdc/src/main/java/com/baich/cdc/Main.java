package com.baich.cdc;


//import com.alibaba.ververica.cdc.connectors.mysql.MySQLSource;
//import com.alibaba.ververica.cdc.debezium.StringDebeziumDeserializationSchema;

import com.alibaba.ververica.cdc.connectors.mysql.MySQLSource;
import com.alibaba.ververica.cdc.debezium.StringDebeziumDeserializationSchema;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.source.SourceFunction;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-11-20
 * Time : 14:04
 * Description:
 * Modified By:
 * version : v1.0
 */
public class Main {
    public static void main(String[] args) throws Exception {
        SourceFunction<String> sourceFunction = MySQLSource.<String>builder()
                .hostname("192.168.10.130")
                .port(3306)
                .databaseList("ods")// monitor all tables under ods database
//                .tableList("t_test")
                .username("root")
                .password("123456")
                .deserializer(new StringDebeziumDeserializationSchema()) // converts SourceRecord to String
                .build();


        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        env
                .addSource(sourceFunction)
                .print().setParallelism(1); // use parallelism 1 for sink to keep message ordering
        env.execute();


    }
}
