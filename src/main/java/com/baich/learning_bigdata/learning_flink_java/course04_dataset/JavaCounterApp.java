package com.baich.learning_bigdata.learning_flink_java.course04_dataset;


import org.apache.flink.api.common.JobExecutionResult;
import org.apache.flink.api.common.accumulators.LongCounter;
import org.apache.flink.api.common.functions.RichMapFunction;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.configuration.Configuration;

import java.util.ArrayList;
import java.util.List;

public class JavaCounterApp {
    public static void main(String[] args) throws Exception {
        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
        List<String> list = new ArrayList<>();
        list.add("Hadoop");
        list.add("Java");
        list.add("Spark");
        list.add("Python");
        DataSource<String> data = env.fromCollection(list);
        DataSet<String> info = data.map(new RichMapFunction<String, String>() {
            LongCounter counter = new LongCounter();

            @Override
            public void open(Configuration parameters) throws Exception {
                getRuntimeContext().addAccumulator("ele-counts-java", counter);
            }

            @Override
            public String map(String value) throws Exception {
                counter.add(1);
                return value;
            }
        });
        info.writeAsText("E:/data/flink/javaoutput/").setParallelism(3);
        JobExecutionResult jobResult = env.execute("JavaCounterApp");
        Object num = jobResult.getAccumulatorResult("ele-counts-java");
        System.out.println("num: " + num);
    }
}
