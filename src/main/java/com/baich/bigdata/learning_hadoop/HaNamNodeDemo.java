package com.baich.bigdata.learning_hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.RemoteIterator;

import java.io.IOException;
import java.util.StringJoiner;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-08-16
 * Time : 14:45
 * Description:
 * Modified By:
 * version : v1.0
 */
public class HaNamNodeDemo {
    private static final String defaultFSStr = "vm01:9000,vm02:9000,vm03:9000";
    private static final String splitter = ",";

    public static void main(String[] args) throws IOException {
        // 进行hadoop的相关配置
        Configuration conf = new Configuration();
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

        // 列出HDFS /user 目录下的文件并打印
        FileSystem fs = FileSystem.get(conf);
        RemoteIterator<LocatedFileStatus> files = fs.listFiles(new Path("/user"), false);
        while (files.hasNext()) {
            System.out.println(files.next().getPath());
        }
        fs.close();
    }
}
