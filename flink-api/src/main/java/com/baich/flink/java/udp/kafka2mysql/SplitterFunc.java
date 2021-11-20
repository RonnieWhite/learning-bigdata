package com.baich.flink.java.udp.kafka2mysql;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-07-08
 * Time : 20:07
 * Description: kafka2mysql FlatMapFunction
 * Modified By:
 * version : v1.0
 */
public class SplitterFunc implements FlatMapFunction<String, Tuple2<String, Long>> {
    @Override
    public void flatMap(String value, Collector<Tuple2<String, Long>> out) throws Exception {
        String[] words = value.toLowerCase().split(",");
        for (String word : words) {
            out.collect(new Tuple2<>(word, 1L));
        }
    }
}
