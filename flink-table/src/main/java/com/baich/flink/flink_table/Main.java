package com.baich.flink.flink_table;

import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.TableEnvironment;

/**
 * Description: 从Kafka主题kafkaInput读取数据，筛选后将指定数据写入kafkaOutput
 * 数据样式：
 * {"id":"1","user_id":"1","status":"1"}
 * {"id":"1","user_id":"1","status":"1"}
 * {"id":"1","user_id":"1","status":"1"}
 * {"id":"2","user_id":"1","status":"1"}
 * {"id":"1","user_id":"1","status":"0"}
 */
public class Main {
    public static void main(String[] args) {
//        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
//        StreamTableEnvironment tEnv = StreamTableEnvironment.create(env);
        EnvironmentSettings settings = EnvironmentSettings.newInstance().inStreamingMode().build();
        TableEnvironment tEnv = TableEnvironment.create(settings);
        tEnv.executeSql("CREATE TABLE kafka_input(id BIGINT,user_id BIGINT," +
                "status STRING) " +
                "WITH ('connector' = 'kafka','topic' = 'kafkaInput'," +
                "'properties.bootstrap.servers' = 'vm01:9092'," +
                "'properties.group.id' = 'testGroup'," +
                "'scan.startup.mode' = 'latest-offset'," +
                "'format' = 'json')");
        Table table = tEnv.sqlQuery("SELECT * FROM kafka_input WHERE status='1'");
        tEnv.executeSql("CREATE TABLE kafka_output(id BIGINT,user_id BIGINT," +
                "status STRING)" +
                " WITH ('connector'='kafka','topic'='kafkaOutput'," +
                "'properties.bootstrap.servers' = 'vm01:9092'," +
                "'format'='json'," +
                "'sink.partitioner'='round-robin')");
        tEnv.executeSql("INSERT INTO kafka_output SELECT * FROM " + table);
        // 会报错：No operators defined in streaming topology. Cannot execute.但不影响程序运行
        // tableEnv.execute("Run TableapiKafkaDemo");
    }
}
