package com.baich.flink.java.udp.hbase2console;

import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * Created By IDEA.
 * Author : PengDH
 * Date : Created in 2021-07-09
 * Time : 09:55
 * Description: Flink 读取HBase数据，版本2修改了代码结构
 * Modified By: BaiCH
 * version : v2.0
 */
public class FlinkHBaseDemo {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStreamSource<String> source = env.addSource(new HBaseReader());
        source.print();
        env.execute("FlinkHBaseDemo");
    }
}
