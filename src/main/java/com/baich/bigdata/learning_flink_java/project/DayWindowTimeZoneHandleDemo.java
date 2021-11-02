package com.baich.bigdata.learning_flink_java.project;

import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.assigners.TumblingProcessingTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.triggers.ContinuousProcessingTimeTrigger;
import org.apache.flink.table.api.TableEnvironment;
import org.apache.flink.table.api.java.StreamTableEnvironment;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-10-11
 * Time : 08:50
 * Description: 演示处理天级别以上时间窗口时的时差导致的计算触发时间问题
 * Modified By:
 * version : v1.0
 */
public class DayWindowTimeZoneHandleDemo {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStream<String> stream = env.socketTextStream("localhost", 9999);
        stream.filter(line -> (line != null && !"".equals(line)))
                .map(line -> new Tuple2<>("1", Integer.valueOf(line)))
                .returns(Types.TUPLE(Types.STRING, Types.INT))
                .keyBy(0)
                // 使用timeWindow()方法，指定窗口时间为一天，且每五秒触发一次计算，此时一天之内的数据将会被累加，
                // 但同时，由于其默认使用的是格林威治时间所属时区，所以对于东八区的我们来说，不太友好
                // （将会以早上八点作为窗口的起始-关闭时间，也就是当天8点前的数据将会归结到前一天的那个窗口里去，这样显然有问题）
//                .timeWindow(Time.days(1))
                // 使用window()方法，此时同样需要指定窗口时间为一天，并补偿时间偏移量：-8
                .window(TumblingProcessingTimeWindows.of(Time.days(1), Time.hours(-8)))
                .trigger(ContinuousProcessingTimeTrigger.of(Time.seconds(5)))
                .sum(1)
                .print();
        env.execute("DayWindowTimeZoneHandleDemo");
    }
}
