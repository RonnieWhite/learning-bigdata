package com.baich.bigdata.learning_spark_java.spark_sql;

import org.apache.spark.SparkConf;
import org.apache.spark.sql.SparkSession;

import java.util.StringJoiner;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-08-19
 * Time : 11:20
 * Description:
 * Modified By: Spark读取hive表，其中Hadoop的连接配置采取HA的方式
 * version : v1.0
 */
public class JavaSparkReadHive {
    private static final String defaultFSStr = "vm01:9000,vm02:9000,vm03:9000";
    private static final String splitter = ",";

    public static void main(String[] args) {
        // 进行spark的相关配置
        SparkConf conf = new SparkConf().setAppName("JavaSparkReadHive").setMaster("local");
        conf.set("fs.defaultFS", "hdfs://hdfsCluster");
        conf.set("dfs.nameservices", "hdfsCluster");
        StringJoiner hostJoiner = new StringJoiner(splitter);
        String[] defaultFSArr = defaultFSStr.split(splitter);
        for (int i = 1; i <= defaultFSArr.length; i++) {
            hostJoiner.add("nn" + 1);
            conf.set("dfs.namenode.rpc-address.hdfsCluster.nn" + i, defaultFSArr[i - 1]);
        }
        conf.set("dfs.ha.namenodes.hdfsCluster", hostJoiner.toString());
        conf.set("dfs.client.failover.proxy.provider.hdfsCluster", "org.apache.hadoop.hdfs.server.namenode.ha.ConfiguredFailoverProxyProvider");

        // 获取hive连接配置
        conf.set("hive.exec.dynamic.partition.mode", "nonstrict");
        conf.set("hive.metastore.uris", "thrift://vm01:9083");

        SparkSession spark = SparkSession.builder()
                .config(conf)
                .enableHiveSupport()
                .getOrCreate();
        spark.sql("SHOW DATABASES").show();
        spark.close();
    }
}
