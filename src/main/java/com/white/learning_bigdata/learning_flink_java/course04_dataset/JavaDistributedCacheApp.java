package com.white.learning_bigdata.learning_flink_java.course04_dataset;

import org.apache.commons.io.FileUtils;
import org.apache.flink.api.common.functions.RichMapFunction;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.configuration.Configuration;

import java.io.File;
import java.util.List;

public class JavaDistributedCacheApp {
    public static void main(String[] args) throws Exception {
        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
        DataSource<String> data = env.fromElements("hadoop", "spark", "android");
        env.registerCachedFile("E:/data/flink/input/abc.txt", "white-java-dc");
        data.map(new RichMapFunction<String, String>() {
            @Override
            public void open(Configuration parameters) throws Exception {
                File file = getRuntimeContext().getDistributedCache().getFile("white-java-dc");
                List<String> list = FileUtils.readLines(file);
                for (String ele : list) {
                    System.out.println(ele);
                }
            }

            @Override
            public String map(String value) {
                return value;
            }
        }).print();

    }
}
