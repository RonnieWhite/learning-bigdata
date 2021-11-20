package com.baich.flink.java;

import org.apache.flink.api.common.accumulators.LongCounter;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-08-04
 * Time : 20:43
 * Description:
 * Modified By:
 * version : v1.0
 * ip,pv,uv
 * 192.168.10.130,1,13
 */
public class AccumulatorDemo {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStream<String> ds = env.socketTextStream("vm01", 9999);
        LongCounter counter = new LongCounter();
        DataStream<Tuple2<String, Long>> pvLongCount = ds.map(line ->
                new Tuple2<>("pv", Long.parseLong(line.split(",")[1]))
        ).returns(Types.TUPLE(Types.STRING, Types.LONG));
        pvLongCount.keyBy(0).timeWindow(Time.seconds(5))
                .sum(1)
                .map(tuple -> {
                    counter.add(tuple.f1);
                    System.out.println(counter.getLocalValue());
                    return tuple;
                }).returns(Types.TUPLE(Types.STRING, Types.LONG)).print();

        env.execute("AccumulatorDemo");
    }
}
