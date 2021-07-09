package com.baich.bigdata.learning_flink_java.udp.hbase2console;

import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.source.RichSourceFunction;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.util.List;

/**
 * Created By IDEA.
 * Author : PengDH
 * Date : Created in 2021-07-07
 * Time : 20:05
 * Description: Flink 读取 HBase数据，修改了之前代码中实现ResultTypeQueryable接口的操作，在继承的RichSourceFunction之上添加泛型
 * Modified By: BaiCH
 * version : v2.0
 */
public class HBaseReader extends RichSourceFunction<String> {
    private Connection connection = null;
    private ResultScanner rs = null;
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
    public void run(SourceContext<String> sourceContext) throws Exception {
        table = connection.getTable(TableName.valueOf("test"));
        Scan scan = new Scan();
        scan.addFamily(Bytes.toBytes("way"));
        rs = table.getScanner(scan);
        for (Result result : rs) {
            StringBuilder sb = new StringBuilder();
            List<Cell> cells = result.listCells();
            for (Cell cell : cells) {
                String value = Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength());
                sb.append(value).append("-");
            }
            String value = sb.replace(sb.length() - 1, sb.length(), "").toString();
            sourceContext.collect(value);
        }
    }

    @Override
    public void cancel() {

    }

    @Override
    public void close() throws Exception {
        if (rs != null) {
            rs.close();
        }
        if (table != null) {
            table.close();
        }
        if (connection != null) {
            connection.close();
        }
    }
}