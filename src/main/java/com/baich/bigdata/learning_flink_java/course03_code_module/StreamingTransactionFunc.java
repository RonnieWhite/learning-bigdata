package com.baich.bigdata.learning_flink_java.course03_code_module;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.util.Collector;

public class StreamingTransactionFunc {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStreamSource<String> text = env.socketTextStream("localhost", 9999);
        text.flatMap(new myFlatMapFunction())
                .keyBy("word")
                .timeWindow(Time.seconds(5))
                .sum("count")
                .print()
                .setParallelism(1);
        env.execute();

    }

    public static class myFlatMapFunction implements FlatMapFunction<String, StreamingKeySelector.WC> {
        @Override
        public void flatMap(String s, Collector<StreamingKeySelector.WC> collector) {
            String[] tokens = s.toLowerCase().split(" ");
            for (String token : tokens) {
                if (token.length() > 0) {
                    collector.collect(new StreamingKeySelector.WC(token, 1));
                }
            }
        }
    }
}
