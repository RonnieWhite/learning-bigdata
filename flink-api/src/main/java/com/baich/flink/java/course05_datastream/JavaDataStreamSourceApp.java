package com.baich.flink.java.course05_datastream;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.util.Collector;

public class JavaDataStreamSourceApp {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
//        socketFunc(env);
//        NonParallelSourceFunc(env);
//        ParallelSourceFunc(env);
        RichParallelSourceFunc(env);
        env.execute();
    }

    private static void socketFunc(StreamExecutionEnvironment env) throws Exception {
        DataStreamSource<String> data = env.socketTextStream("localhost", 9999);
        data.flatMap(new FlatMapFunction<String, Tuple2<String, Integer>>() {
            @Override
            public void flatMap(String value, Collector<Tuple2<String, Integer>> out) {
                String[] words = value.toLowerCase().split(" ");
                for (String word : words) {
                    out.collect(new Tuple2<>(word, 1));
                }
            }
        }).keyBy(0)
                .timeWindow(Time.seconds(5))
                .sum(1)
                .setParallelism(2)
                .print();
    }

    private static void NonParallelSourceFunc(StreamExecutionEnvironment env) throws Exception {
        DataStreamSource<Long> dataStreamSource = env.addSource(new JavaCustomNonParallelSourceFunc());
        dataStreamSource.print();
    }

    private static void ParallelSourceFunc(StreamExecutionEnvironment env) throws Exception {
        DataStreamSource<Long> data = env.addSource(new JavaCustomParallelSourceFunc()).setParallelism(2);
        data.print();
    }

    private static void RichParallelSourceFunc(StreamExecutionEnvironment env) throws Exception {
        DataStreamSource<Long> data = env.addSource(new JavaCustomRichParallelSourceFunc()).setParallelism(3);
        data.print();
    }
}
