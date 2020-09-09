package com.baich.bigdata.learning_flink_java.course04_dataset;

import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.core.fs.FileSystem;

import java.util.ArrayList;
import java.util.List;

public class JavaDataSetSinkApp {
    public static void main(String[] args) throws Exception {
        List<Integer> list = new ArrayList<>();
        for (
                int i = 1;
                i <= 10; i++) {
            list.add(i);
        }

        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
        DataSource<Integer> data = env.fromCollection(list);
        data.writeAsText("E:/data/flink/javaoutput/file", FileSystem.WriteMode.NO_OVERWRITE).setParallelism(2);
        env.execute();
    }
}
