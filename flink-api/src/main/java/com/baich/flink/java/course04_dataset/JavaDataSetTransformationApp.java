package com.baich.flink.java.course04_dataset;

import org.apache.flink.api.common.functions.*;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.CrossOperator;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.util.Collector;

import java.util.ArrayList;
import java.util.List;

public class JavaDataSetTransformationApp {
    public static void main(String[] args) throws Exception {
        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
//        mapFunc(env);
//        filterFunc(env);
//        mapPartitionFunc(env);
//        flatMapFunc(env);
//        joinFunc(env);
        crossFunc(env);
    }

    public static void mapFunc(ExecutionEnvironment env) throws Exception {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 1; i < 10; i++) {
            list.add(i);
        }
        DataSource<Integer> data = env.fromCollection(list);
        data.map(new MapFunction<Integer, Integer>() {
            @Override
            public Integer map(Integer input) {
                return input * 2;
            }
        }).print();
    }

    public static void filterFunc(ExecutionEnvironment env) throws Exception {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 1; i < 10; i++) {
            list.add(i);
        }
        DataSource<Integer> data = env.fromCollection(list).setParallelism(3);
        data.filter(new FilterFunction<Integer>() {
            @Override
            public boolean filter(Integer input) throws Exception {
                return input > 4;
            }
        }).print();
    }

    public static void mapPartitionFunc(ExecutionEnvironment env) throws Exception {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 1; i < 10; i++) {
            list.add(i);
        }
        DataSource<Integer> data = env.fromCollection(list);
        data.mapPartition(new MapPartitionFunction<Integer, Integer>() {
            @Override
            public void mapPartition(Iterable<Integer> values, Collector<Integer> out) {
                for (int value : values) {
                    out.collect(value * 2);
                }
            }
        }).first(3).print();

    }

    public static void flatMapFunc(ExecutionEnvironment env) throws Exception {
        List<String> list = new ArrayList<String>();
        list.add("hadoop,flink");
        list.add("spark,linux");
        list.add("jdk,oracle,jdk,oracle");
        DataSource<String> data = env.fromCollection(list);
        data.flatMap(new FlatMapFunction<String, String>() {
            @Override
            public void flatMap(String value, Collector<String> out) {
                String[] words = value.split(",");
                for (String word : words) {
                    out.collect(word);
                }
            }
        }).distinct().print();
    }

    public static void joinFunc(ExecutionEnvironment env) throws Exception {
        List<Tuple2<Integer, String>> list1 = new ArrayList<>();
        List<Tuple2<Integer, String>> list2 = new ArrayList<>();
        list1.add(new Tuple2<>(1, "Jojo"));
        list1.add(new Tuple2<>(2, "Davis"));
        list2.add(new Tuple2<>(1, "beijing"));
        list2.add(new Tuple2<>(2, "shanghai"));
        list2.add(new Tuple2<>(3, "guiyang"));
        DataSource<Tuple2<Integer, String>> data1 = env.fromCollection(list1);
        DataSource<Tuple2<Integer, String>> data2 = env.fromCollection(list2);
        // join
//        data1.join(data2).where(0).equalTo(0).with(new JoinFunction<Tuple2<Integer, String>, Tuple2<Integer, String>, Tuple3<Integer, String, String>>() {
//            @Override
//            public Tuple3<Integer, String, String> join(Tuple2<Integer, String> first, Tuple2<Integer, String> second) {
//                return new Tuple3<>(first.f0, first.f1, second.f1);
//            }
//        }).print();
        // outerJoin
//        data1.leftOuterJoin(data2).where(0).equalTo(0).with(new JoinFunction<Tuple2<Integer, String>, Tuple2<Integer, String>, Tuple3<Integer, String, String>>() {
//            @Override
//            public Tuple3<Integer, String, String> join(Tuple2<Integer, String> first, Tuple2<Integer, String> second) {
//                return new Tuple3<>(first.f0, first.f1, second.f1);
//            }
//        }).print();
//        fullOuterJoin
        data1.fullOuterJoin(data2).where(0).equalTo(0).with(new JoinFunction<Tuple2<Integer, String>, Tuple2<Integer, String>, Tuple3<Integer, String, String>>() {
            @Override
            public Tuple3<Integer, String, String> join(Tuple2<Integer, String> first, Tuple2<Integer, String> second) {
                if (first == null) {
                    return new Tuple3<>(second.f0, "-", second.f1);
                } else if (second == null) {
                    return new Tuple3<>(first.f0, first.f1, "-");
                } else {
                    return new Tuple3<>(first.f0, first.f1, second.f1);
                }
            }
        }).print();
    }

    public static void crossFunc(ExecutionEnvironment env) throws Exception {
        List<String> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        list1.add("曼城");
        list1.add("曼联");
        list2.add(1);
        list2.add(2);
        list2.add(3);
        DataSource<String> data1 = env.fromCollection(list1);
        DataSource<Integer> data2 = env.fromCollection(list2);
        CrossOperator.DefaultCross<String, Integer> data3 = (CrossOperator.DefaultCross<String, Integer>) data1.cross(data2);
        data3.print();
    }
}
