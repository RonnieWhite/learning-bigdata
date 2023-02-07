package com.baich.flink.java.event_time;

import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.functions.ProcessFunction;
import org.apache.flink.util.Collector;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2023-02-06
 * Time : 16:47
 * Description:
 * Modified By:
 * version : v1.0
 */
public class EmployeeProcessFunction extends ProcessFunction<EmployeeIncome, Tuple2<String, Float>> {

    @Override
    public void processElement(EmployeeIncome value, ProcessFunction<EmployeeIncome, Tuple2<String, Float>>.Context ctx, Collector<Tuple2<String, Float>> out) throws Exception {
        System.out.println("+++++++++++");
        out.collect(new Tuple2<>(value.getEmployeeName(), value.getIncome()));
//        ctx.timerService().registerEventTimeTimer( + 1000);
//        ctx.timerService().registerProcessingTimeTimer(System.currentTimeMillis());
        System.out.println(ctx.timestamp());
        System.out.println("@@@@@@@@@@@@@@@@@@@@@");
        System.out.println(value.getFlowTime() * 1000);
        ctx.timerService().registerEventTimeTimer(ctx.timestamp());
    }

    @Override
    public void onTimer(long timestamp, ProcessFunction<EmployeeIncome, Tuple2<String, Float>>.OnTimerContext ctx, Collector<Tuple2<String, Float>> out) throws Exception {
        super.onTimer(timestamp, ctx, out);
        System.out.println("########");
        System.out.println(timestamp);
        System.out.println("=========");
    }

}
