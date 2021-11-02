package com.baich.bigdata.learning_flink_java.project;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.common.state.ValueState;
import org.apache.flink.api.common.state.ValueStateDescriptor;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.KeyedProcessFunction;
import org.apache.flink.streaming.api.windowing.assigners.TumblingProcessingTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.util.Collector;

import java.util.Properties;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-10-15
 * Time : 10:35
 * Description:
 * Modified By:
 * version : v1.0
 */
public class TempConRiseWarningDemo {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);
//        DataStreamSource<String> inputStream = env.socketTextStream("localhost", 9999);
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "vm01:9092");
        properties.setProperty("group.id", "consumer-group");
        properties.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.setProperty("auto.offset.reset", "latest");
        DataStreamSource<String> inputStream = env.addSource(new FlinkKafkaConsumer<String>("test", new SimpleStringSchema(), properties));
        KeyedStream<Tuple2<String, Double>, String> keyedStream = inputStream.map(new MapFunction<String, Tuple2<String, Double>>() {
            @Override
            public Tuple2<String, Double> map(String value) throws Exception {
                String[] fields = value.split(",");
                return new Tuple2<>(fields[0], new Double(fields[1]));
            }
        }).keyBy(tuple2 -> tuple2.f0);
        keyedStream.window(TumblingProcessingTimeWindows.of(Time.seconds(10)));
        keyedStream.process(new TempConRiseWarning(10)).print();
        env.execute("Run TempConRiseWarningDemo");
    }

    static class TempConRiseWarning extends KeyedProcessFunction<String, Tuple2<String, Double>, String> {
        private Integer interval;

        public TempConRiseWarning(Integer interval) {
            this.interval = interval;
        }

        // 定义状态，保存上一次的温度值以及定时器时间戳
        private ValueState<Double> lastTempState;
        private ValueState<Long> timerTsState;

        @Override
        public void open(Configuration parameters) throws Exception {
            lastTempState = getRuntimeContext().getState(new ValueStateDescriptor<Double>("last-temp-state", Double.class));
            timerTsState = getRuntimeContext().getState(new ValueStateDescriptor<Long>("timer-timestamp-state", Long.class));
        }

        @Override
        public void processElement(Tuple2<String, Double> value, Context ctx, Collector<String> out) throws Exception {
            // 取出值
            Double lastTemp = lastTempState.value();
            Long timerTs = timerTsState.value();
            // 如果上次温度值为null，赋值为Double的最小值
            if (lastTemp == null) {
                lastTemp = Double.MIN_VALUE;
            }
            // 如果温度上升并且没有定时器，注册定时器，开始等待
            if (lastTemp < value.f1 && timerTs == null) {
                // 获取定时器时间戳
                long ts = ctx.timerService().currentProcessingTime() + interval * 1000L;
                // 注册定时器
                ctx.timerService().registerProcessingTimeTimer(ts);
                // 设置时间戳
                timerTsState.update(ts);
            } else if (lastTemp > value.f1 && timerTs != null) { // 如果温度下降，删除定时器
                // 删除定时器
                ctx.timerService().deleteProcessingTimeTimer(timerTs);
                // 清除时间戳
                timerTsState.clear();
            }
            // 更新温度值
            lastTempState.update(value.f1);
        }

        @Override
        public void onTimer(long timestamp, OnTimerContext ctx, Collector<String> out) throws Exception {
            // 输出警告
            out.collect("Warning! the temperature of " + ctx.getCurrentKey() + " has risen continuously in last " + interval + " seconds.");
            // 清除定时器时间戳
            timerTsState.clear();
        }

        @Override
        public void close() throws Exception {
            lastTempState.clear();
            timerTsState.clear();
        }
    }
}
