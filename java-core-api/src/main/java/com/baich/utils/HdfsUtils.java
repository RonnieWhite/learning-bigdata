package com.baich.utils;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FileUtil;
import org.apache.hadoop.fs.LocalFileSystem;
import org.apache.hadoop.fs.Path;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-07-27
 * Time : 21:44
 * Description: 这里主要演示用FileUtil对小文件进行合并并下载到本地的操作
 * Modified By:
 * version : v1.0
 */
public class HdfsUtils {
    public static void getMerge() throws Exception {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "hdfs://vm01:9000");
        System.setProperty("HADOOP_USER_NAME", "root");
        FileSystem srcFS = FileSystem.get(conf);
        Path srcPath = new Path("/data");
        LocalFileSystem localFS = FileSystem.getLocal(conf);
        Path localPath = new Path("D:/data");
        FileUtil.copyMerge(srcFS, srcPath, localFS, localPath, true, conf, "");
    }
}
