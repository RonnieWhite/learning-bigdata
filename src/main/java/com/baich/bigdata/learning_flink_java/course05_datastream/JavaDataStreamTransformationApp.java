package com.baich.bigdata.learning_flink_java.course05_datastream;

import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.collector.selector.OutputSelector;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SplitStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.util.ArrayList;
import java.util.List;

public class JavaDataStreamTransformationApp {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
//        mapFilterFunc(env);
//        unionFunc(env);
        splitSelectFunc(env);
        env.execute("JavaDataStreamTransformationApp");

    }

    public static void mapFilterFunc(StreamExecutionEnvironment env) {
        DataStreamSource<Long> data = env.addSource(new JavaCustomNonParallelSourceFunc());
        data.map(new MapFunction<Long, Long>() {
            @Override
            public Long map(Long value) throws Exception {
                System.out.println("value: " + value);
                return value;
            }
        }).filter(new FilterFunction<Long>() {
            @Override
            public boolean filter(Long value) throws Exception {
                return value % 2 == 0;
            }
        }).print();
    }

    public static void unionFunc(StreamExecutionEnvironment env) {
        DataStreamSource<Long> data1 = env.addSource(new JavaCustomNonParallelSourceFunc());
        DataStreamSource<Long> data2 = env.addSource(new JavaCustomNonParallelSourceFunc());
        data1.union(data2).print();
    }

    public static void splitSelectFunc(StreamExecutionEnvironment env) {
        DataStreamSource<Long> data = env.addSource(new JavaCustomNonParallelSourceFunc());
        SplitStream<Long> splits = data.split(new OutputSelector<Long>() {
            @Override
            public Iterable<String> select(Long value) {
                List<String> list = new ArrayList<>();
                if (value % 2 == 0) {
                    list.add("even");
                } else {
                    list.add("odd");
                }
                return list;
            }
        });
        splits.select("even").print();

    }


}
