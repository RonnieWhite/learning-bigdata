package main.java.learning_flink.course04_dataset;

import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.DataSource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JavaDataSetTransformationApp {
    public static void main(String[] args) throws Exception {
        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
//        mapFunc(env);
        filterFunc(env);

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
        DataSource<Integer> data = env.fromCollection(list);
        data.filter(new FilterFunction<Integer>() {
            @Override
            public boolean filter(Integer input) throws Exception {
                return input > 4;
            }
        }).print();
    }
}
