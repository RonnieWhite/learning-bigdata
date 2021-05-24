package com.baich.bigdata.learning_flink_java.source_code;

import org.apache.flink.api.common.operators.Ordering;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.io.SplitDataProperties;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.types.StringValue;

public class EnvTest001 {
    public static void main(String[] args) throws Exception {
        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
        DataSource<StringValue> data = env.readTextFileWithValue("D:\\data\\xxx.txt");
        data.print();
    }
}
