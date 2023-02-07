package com.baich.flink.java.event_time;

import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;

import java.time.Duration;
import java.util.Properties;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2023-02-06
 * Time : 16:40
 * Description:
 * Modified By:
 * version : v1.0
 */
public class ProcessDemo02 {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        Properties props = new Properties();
        props.setProperty("bootstrap.servers", "vm01:9092");
        props.setProperty("group.id", "test_process");
        props.setProperty("auto.offset.reset", "latest"); // latest/earliest
        props.setProperty("enable.auto.commit", "true");
        props.setProperty("auto.commit.interval.ms", "3000");
        FlinkKafkaConsumer<String> consumer = new FlinkKafkaConsumer<String>("employee", new SimpleStringSchema(), props);
        env.addSource(consumer).map(new Str2EmpIncomeMapFunction())
                .assignTimestampsAndWatermarks(WatermarkStrategy.<EmployeeIncome>forBoundedOutOfOrderness(Duration.ofSeconds(10))
                        .withTimestampAssigner((event, timestamp) -> event.getFlowTime() * 1000))
                .keyBy(EmployeeIncome::getEmployeeName)
                .process(new EmployeeProcessFunction())
                .keyBy(tuple2 -> tuple2.f0)
                .window(TumblingEventTimeWindows.of(Time.seconds(10)))
                .sum(1)
//                .process(new EmployeeWindowProcessFunction())
                .print();


        env.execute("ProcessDemo02");
    }
}
