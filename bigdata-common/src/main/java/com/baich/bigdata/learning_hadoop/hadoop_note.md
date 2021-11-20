*方法与core-site.xml hdfs-site.xml中配置相关参数类型，Java Api中，代码如下*
```java
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.RemoteIterator;

import java.io.IOException;
import java.util.StringJoiner;

public class HaNamNodeDemo {
    private static final String defaultFSStr = "vm01:9000,vm02:9000,vm03:9000";
    private static final String splitter = ",";

    public static void main(String[] args) throws IOException {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "hdfs://hdfsCluster");
        conf.set("dfs.nameservices", "hdfsCluster");
        StringJoiner hostJoiner = new StringJoiner(splitter);
        String[] defaultFSArr = defaultFSStr.split(splitter);
        for (int i = 1; i <= defaultFSArr.length; i++) {
            hostJoiner.add("nn" + 1);
//            conf.set("dfs.namenode.rpc-address.hdfsCluster.nn1", "vm01:9000");
//            conf.set("dfs.namenode.rpc-address.hdfsCluster.nn2", "vm02:9000");
//            conf.set("dfs.namenode.rpc-address.hdfsCluster.nn3", "vm03:9000");
            conf.set("dfs.namenode.rpc-address.hdfsCluster.nn" + i, defaultFSArr[i - 1]);
        }
//        conf.set("dfs.ha.namenodes.hdfsCluster", "nn1,nn2,nn3");
        conf.set("dfs.ha.namenodes.hdfsCluster", hostJoiner.toString());
        // 关键配置
        conf.set("dfs.client.failover.proxy.provider.hdfsCluster", "org.apache.hadoop.hdfs.server.namenode.ha.ConfiguredFailoverProxyProvider");
        FileSystem fs = FileSystem.get(conf);
        RemoteIterator<LocatedFileStatus> files = fs.listFiles(new Path("/user"), false);
        while (files.hasNext()) {
            System.out.println(files.next().getPath());
        }
        fs.close();

    }
}

```