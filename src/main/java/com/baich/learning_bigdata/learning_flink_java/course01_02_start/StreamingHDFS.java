package com.baich.learning_bigdata.learning_flink_java.course01_02_start;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.util.Collector;

public class StreamingHDFS {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStreamSource<String> source = env.readTextFile("hdfs://myspark:9000/flume/log");
        source.flatMap(new wordSplitter()).keyBy(0).timeWindow(Time.seconds(10)).sum(1).print();
//        SingleOutputStreamOperator<Tuple2<String, Integer>> sum = source.flatMap(new wordSplitter()).keyBy(0).timeWindow(Time.seconds(10)).sum(1);
//        sum.writeAsText("hdfs://myspark:9000/ttt");
        env.execute("StreamingHDFS");
    }

    static class wordSplitter implements FlatMapFunction<String, Tuple2<String, Integer>> {

        @Override
        public void flatMap(String value, Collector<Tuple2<String, Integer>> out) throws Exception {
            String[] words = value.toLowerCase().split(",");
            for (String word : words) {
                out.collect(new Tuple2<>(word, 1));
            }
        }
    }
}
