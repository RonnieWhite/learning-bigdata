package com.white.learning_bigdata.learning_flink_java.course04_dataset;

import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.configuration.Configuration;

import java.util.ArrayList;
import java.util.List;

public class JavaDataSetDataSourceApp {
    public static void main(String[] args) throws Exception {
        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
//        fromCollection(env);
//        readTextFile(env);
        readCsvFile(env);
//        readRecursiveFiles(env);

    }

    public static void fromCollection(ExecutionEnvironment env) throws Exception {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            list.add(i);
        }
        env.fromCollection(list).print();
    }

    public static void readTextFile(ExecutionEnvironment env) throws Exception {
        env.readTextFile("E:/data/flink/input").print();
    }

    public static void readCsvFile(ExecutionEnvironment env) throws Exception {
//        DataSet<Tuple3<String, Integer, String>> out = env.readCsvFile("E:/data/flink/input/person.csv").ignoreFirstLine().types(String.class, Integer.class, String.class);
        DataSet<Person> out = env.readCsvFile("E:/data/flink/input/person.csv").ignoreFirstLine().pojoType(Person.class, "name", "age", "job");
        out.print();
    }

    public static void readRecursiveFiles(ExecutionEnvironment env) throws Exception {
        Configuration parameters = new Configuration();
        parameters.setBoolean("recursive.file.enumeration", true);
        env.readTextFile("E:/data/flink/input/recursive_test").withParameters(parameters).print();
    }

    public static class Person {
        private String name;
        private int age;
        private String job;

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        public String getJob() {
            return job;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public void setJob(String job) {
            this.job = job;
        }

        @Override
        public String toString() {
            return name + "---" + age + "---" + job;
        }
    }
}
