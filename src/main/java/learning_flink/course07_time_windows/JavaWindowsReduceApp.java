package main.java.learning_flink.course07_time_windows;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.ReduceFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.util.Collector;

public class JavaWindowsReduceApp {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStreamSource<String> data = env.socketTextStream("localhost", 9999);
        data.flatMap(new FlatMapFunction<String, Tuple2<Integer, Integer>>() {
            @Override
            public void flatMap(String value, Collector<Tuple2<Integer, Integer>> out) throws Exception {
                String[] numStrArr = value.split(",");
                for (String numStr : numStrArr) {
                    int num = Integer.parseInt(numStr);
                    out.collect(new Tuple2<>(1, num));
                }
            }
        }).keyBy(0)
                .timeWindow(Time.seconds(5))
                .reduce((value1, value2) -> {
                    return new Tuple2<>(value1.f0, value1.f1 + value2.f1);
                })
//                .reduce(new ReduceFunction<Tuple2<Integer, Integer>>() {
//                    @Override
//                    public Tuple2<Integer, Integer> reduce(Tuple2<Integer, Integer> value1, Tuple2<Integer, Integer> value2) throws Exception {
//                        return new Tuple2<>(value1.f0, value1.f1 + value2.f1);
//                    }
//                })
                .print();
        env.execute();
    }
}
