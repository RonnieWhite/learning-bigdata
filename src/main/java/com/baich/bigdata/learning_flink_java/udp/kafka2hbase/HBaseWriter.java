package com.baich.bigdata.learning_flink_java.udp.kafka2hbase;

import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.hbase.util.MD5Hash;

import java.io.IOException;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-07-09
 * Time : 10:32
 * Description:
 * Modified By:
 * version : v1.0

 */
public class HBaseWriter extends RichSinkFunction<String> {
    private Connection connection = null;
    private Table table = null;

    @Override
    public void open(Configuration parameters) throws Exception {
        org.apache.hadoop.conf.Configuration hconf = HBaseConfiguration.create();
        hconf.set("hbase.zookeeper.quorum", "vm01:2181");
        hconf.set("zookeeper.znode.parent", "/hbase");
        if (connection == null) {
            connection = ConnectionFactory.createConnection(hconf);
        }
    }

    @Override
    public void invoke(String value, Context context) throws IOException {
        table = connection.getTable(TableName.valueOf("test"));
        System.out.println(value);
        String[] way = value.split(",");
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
        table.put(put);
    }

    @Override
    public void close() throws Exception {
        if (table != null) {
            table.close();
        }
        if (connection != null) {
            connection.close();
        }
    }
}
