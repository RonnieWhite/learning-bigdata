package com.baich.flink.java.watermark;

import org.apache.calcite.linq4j.Ord;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.datastream.WindowedStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.ProcessFunction;
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;

import java.time.Duration;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-11-30
 * Time : 17:41
 * Description:
 * Modified By:
 * version : v1.0
 */
public class WatermarkDemo01 {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStream<Order> stream = env.socketTextStream("192.168.232.130", 9999)
                .process(new ProcessFunction<String, Order>() {
                    @Override
                    public void processElement(String s, ProcessFunction<String, Order>.Context context, Collector<Order> collector) throws Exception {
                        String[] fields = s.split(",");
                        if (fields.length < 3) return;
                        Order order = new Order(fields[0], Integer.parseInt(fields[1]), Long.parseLong(fields[2]));
                        System.out.println(order);
                        System.out.println(order.getEventTime());
                        collector.collect(order);
                    }
                });
        stream.assignTimestampsAndWatermarks(
                        WatermarkStrategy.<Order>forBoundedOutOfOrderness(Duration.ofSeconds(3))
                                .withTimestampAssigner((order, timestamp) -> order.getEventTime()))
                .keyBy(Order::getOrderId)
                .window(TumblingEventTimeWindows.of(Time.seconds(5)))
                .sum("money")
                .print();

        env.execute("WatermarkDemo01");
    }
}
