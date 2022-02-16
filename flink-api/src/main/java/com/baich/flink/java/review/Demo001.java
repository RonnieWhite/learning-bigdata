package com.baich.flink.java.review;

import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment;

public class Demo001 {
    public static void main(String[] args) {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.registerCachedFile("hdfs://vm01:9000/data/a.json","myFile");
    }
}
