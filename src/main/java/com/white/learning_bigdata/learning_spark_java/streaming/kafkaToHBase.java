package com.white.learning_bigdata.learning_spark_java.streaming;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.hbase.util.MD5Hash;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.spark.SparkConf;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka010.ConsumerStrategies;
import org.apache.spark.streaming.kafka010.KafkaUtils;
import org.apache.spark.streaming.kafka010.LocationStrategies;

import java.io.IOException;
import java.util.*;

public class kafkaToHBase {
    public static void main(String[] args) throws Exception {
        SparkConf conf = new SparkConf().setAppName("KafkaToHBase").setMaster("local[2]");
        JavaStreamingContext jssc = new JavaStreamingContext(conf, Durations.seconds(5));
        Map<String, Object> kafkaParams = new HashMap<>();
        kafkaParams.put("group.id", "DefaultConsumerGroup");
        kafkaParams.put("bootstrap.servers", "vm01:9092");
        kafkaParams.put("key.deserializer", StringDeserializer.class);
        kafkaParams.put("value.deserializer", StringDeserializer.class);
        // 设置偏移量
        /*
            1、earliest
               当各分区下有已提交的offset时，从提交的offset开始消费，无提交的offset时，从头开始消费
            2、latest
                当各分区下有已提交的offset时，从提交的offset开始消费，无提交的offset时，消费新产生的该分区下的数据
            3、none
                topic各分区都存在已提交的offset时，从提交的offset后开始消费，只要有一个分区不存在已提交的offset，则抛出异常
        */
        kafkaParams.put("auto.offset.reset", "latest");
        // 不自动提交偏移量
        kafkaParams.put("enable.auto.commit", false);
        Collection<String> topic = Collections.singleton("test");
        JavaInputDStream<ConsumerRecord<Object, Object>> kafkaStream = KafkaUtils.createDirectStream(
                jssc,
                LocationStrategies.PreferConsistent(),
                ConsumerStrategies.Subscribe(topic, kafkaParams));
        Configuration hconf = HBaseConfiguration.create();
        hconf.set("hbase.zookeeper.quorum", "vm01:2181");
        hconf.set("zookeeper.znode.parent", "/hbase");
        Connection connection = ConnectionFactory.createConnection(hconf);
        TableName tableName = TableName.valueOf("test");
        Table table = connection.getTable(tableName);
        kafkaStream.foreachRDD(rdds -> {
            rdds.foreachPartition(partition -> {
                ArrayList<Put> puts = new ArrayList<>();
                try {
                    partition.forEachRemaining(arr -> {
                        String[] way = arr.value().toString().split(",");
                        // 1,2020-06-01 00:01:02,2,69,45
                        String id = way[0];
                        String timeStamp = way[1];
                        String typeId = way[2];
                        String latitude = way[3];
                        String longitude = way[4];
                        String rowKey = MD5Hash.getMD5AsHex(Bytes.toBytes(timeStamp + typeId + longitude + latitude));
                        Put put = new Put(Bytes.toBytes(rowKey));
                        put.addColumn(Bytes.toBytes("way"), Bytes.toBytes("id"), Bytes.toBytes(id));
                        put.addColumn(Bytes.toBytes("way"), Bytes.toBytes("ts"), Bytes.toBytes(timeStamp));
                        put.addColumn(Bytes.toBytes("way"), Bytes.toBytes("typeid"), Bytes.toBytes(typeId));
                        put.addColumn(Bytes.toBytes("way"), Bytes.toBytes("longitude"), Bytes.toBytes(longitude));
                        put.addColumn(Bytes.toBytes("way"), Bytes.toBytes("latitude"), Bytes.toBytes(latitude));
                        puts.add(put);
                        try {
                            table.put(puts);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        puts.clear();
                    });
                }catch (Exception e){
                    e.printStackTrace();
                }
                table.close();
            });
        });
        jssc.start();
        jssc.awaitTermination();
    }
}
