package main.java.learning_spark.spark_sql;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.SQLContext;

public class RDD2DataFrameRedlection {
    public static void main(String[] args) {
        // 创建普通的RDD
        SparkConf conf = new SparkConf().setMaster("local").setAppName("RDD2DataFrameRedlection");
        JavaSparkContext sc = new JavaSparkContext(conf);
        SQLContext sqlContext = new SQLContext(sc);
        JavaRDD<String> lines = sc.textFile("E:/data/spark/students.txt");
//        lines.map(new Function<String, Student>() {
//            @Override
//            public Student call(String line) throws Exception {
//                String[] info = line.split(",");
//
//            }
//        });
    }

    class Student {
        private int id;
        private String name;
        private int age;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}
