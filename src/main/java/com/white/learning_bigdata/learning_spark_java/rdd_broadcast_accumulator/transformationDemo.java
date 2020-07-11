package com.white.learning_bigdata.learning_spark_java.rdd_broadcast_accumulator;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.*;
import scala.Tuple2;

import java.util.Arrays;
import java.util.List;

public class transformationDemo {
    public static void main(String[] args) {

        SparkConf conf = new SparkConf().setAppName("td").setMaster("local[1]");
        JavaSparkContext jsc = new JavaSparkContext(conf);

        // 所有元素乘以2
//        map(jsc);
        // 过滤元素
//        filter(jsc);
//        groupByKey(jsc);
//        reduceByKey(jsc);
//        join(jsc);
        cogroup(jsc);
        jsc.close();
    }


    private static void map(JavaSparkContext jsc) {
        List<Integer> list = Arrays.asList(1, 3, 5, 7, 2, 4, 6);
        JavaRDD<Integer> rdd = jsc.parallelize(list);
        JavaRDD<Integer> map = rdd.map(new Function<Integer, Integer>() {
            @Override
            public Integer call(Integer v1) throws Exception {
                return v1 * 2;
            }
        });
        map.foreach(new VoidFunction<Integer>() {
            @Override
            public void call(Integer integer) throws Exception {
                System.out.println(integer);
            }
        });
    }

    private static void filter(JavaSparkContext jsc) {
        List<Integer> list = Arrays.asList(1, 3, 5, 7, 2, 4, 6);
        JavaRDD<Integer> rdd = jsc.parallelize(list);
//        JavaPairRDD<String, Integer> rdd = jsc.parallelizePairs(list);
        JavaRDD<Integer> filter = rdd.filter(new Function<Integer, Boolean>() {
            @Override
            public Boolean call(Integer v1) throws Exception {
                return v1 % 2 == 0;
            }
        });
        filter.foreach(new VoidFunction<Integer>() {
            @Override
            public void call(Integer integer) throws Exception {
                System.out.println(integer);
            }
        });
    }

    private static void groupByKey(JavaSparkContext jsc) {
        List<Tuple2<String, Integer>> list = Arrays.asList(new Tuple2<>("class1", 80),
                new Tuple2<>("class2", 90),
                new Tuple2<>("class1", 85),
                new Tuple2<>("class2", 75));
        JavaRDD<Tuple2<String, Integer>> rdd = jsc.parallelize(list);
        JavaPairRDD<String, Iterable<Integer>> group = rdd.mapToPair(new PairFunction<Tuple2<String, Integer>, String, Integer>() {
            @Override
            public Tuple2<String, Integer> call(Tuple2<String, Integer> tuple2) throws Exception {
                return new Tuple2<>(tuple2._1, tuple2._2);
            }
        }).groupByKey();
        JavaRDD<Tuple2<String, Integer>> map = group.map(new Function<Tuple2<String, Iterable<Integer>>, Tuple2<String, Integer>>() {
            @Override
            public Tuple2<String, Integer> call(Tuple2<String, Iterable<Integer>> v1) throws Exception {
                int counter = 0;
                for (int i : v1._2) {
                    counter += i;
                }
                return new Tuple2<>(v1._1, counter);
            }
        });
//        group.foreach(new VoidFunction<Tuple2<String, Iterable<Integer>>>() {
//            @Override
//            public void call(Tuple2<String, Iterable<Integer>> tuple2) throws Exception {
//                System.out.println(tuple2._1 + ":" + tuple2._2);
//            }
//        });
        map.foreach(new VoidFunction<Tuple2<String, Integer>>() {
            @Override
            public void call(Tuple2<String, Integer> tuple2) throws Exception {
                System.out.println(tuple2._1 + ":" + tuple2._2);
            }
        });

    }

    private static void reduceByKey(JavaSparkContext jsc) {
        List<Tuple2<String, Integer>> list = Arrays.asList(new Tuple2<>("class1", 80),
                new Tuple2<>("class2", 90),
                new Tuple2<>("class1", 85),
                new Tuple2<>("class2", 75));
        JavaRDD<Tuple2<String, Integer>> rdd = jsc.parallelize(list);
        JavaPairRDD<String, Integer> reduce = rdd.mapToPair(new PairFunction<Tuple2<String, Integer>, String, Integer>() {
            @Override
            public Tuple2<String, Integer> call(Tuple2<String, Integer> tuple2) throws Exception {
                return new Tuple2<>(tuple2._1, tuple2._2);
            }
        }).reduceByKey(new Function2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer v1, Integer v2) throws Exception {
                return v1 + v2;
            }
        });
        reduce.foreach(new VoidFunction<Tuple2<String, Integer>>() {
            @Override
            public void call(Tuple2<String, Integer> tuple2) throws Exception {
                System.out.println(tuple2._1 + ":" + tuple2._2);
            }
        });
    }

    private static void join(JavaSparkContext jsc) {
        List<Tuple2<Integer, String>> list1 = Arrays.asList(new Tuple2<>(1, "jojo"),
                new Tuple2<>(2, "jay"),
                new Tuple2<>(3, "jack"),
                new Tuple2<>(4, "jim"));
        List<Tuple2<Integer, Integer>> list2 = Arrays.asList(new Tuple2<>(2, 88),
                new Tuple2<>(1, 98),
                new Tuple2<>(4, 68),
                new Tuple2<>(3, 78));
        JavaPairRDD<Integer, String> names = jsc.parallelizePairs(list1);
        JavaPairRDD<Integer, Integer> scores = jsc.parallelizePairs(list2);
        JavaPairRDD<Integer, Tuple2<String, Integer>> join = (JavaPairRDD<Integer, Tuple2<String, Integer>>) names.join(scores);
        join.foreach(new VoidFunction<Tuple2<Integer, Tuple2<String, Integer>>>() {
            @Override
            public void call(Tuple2<Integer, Tuple2<String, Integer>> integerTuple2Tuple2) throws Exception {
                System.out.println(integerTuple2Tuple2._1 + ":" + "(" + integerTuple2Tuple2._2._1 + "," + integerTuple2Tuple2._2._2 + ")");
            }
        });
    }

    private static void cogroup(JavaSparkContext jsc) {
        List<Tuple2<Integer, String>> list1 = Arrays.asList(new Tuple2<>(1, "jojo"),
                new Tuple2<>(2, "jay"),
                new Tuple2<>(3, "jack"),
                new Tuple2<>(4, "jim"));
        List<Tuple2<Integer, Integer>> list2 = Arrays.asList(new Tuple2<>(2, 88),
                new Tuple2<>(1, 98),
                new Tuple2<>(4, 68),
                new Tuple2<>(4, 38),
                new Tuple2<>(4, 48),
                new Tuple2<>(4, 58),
                new Tuple2<>(3, 78));
        JavaPairRDD<Integer, String> names = jsc.parallelizePairs(list1);
        JavaPairRDD<Integer, Integer> scores = jsc.parallelizePairs(list2);
        JavaPairRDD<Integer, Tuple2<Iterable<String>, Iterable<Integer>>> cogroup = (JavaPairRDD<Integer, Tuple2<Iterable<String>, Iterable<Integer>>>) names.cogroup(scores);
        cogroup.foreach(new VoidFunction<Tuple2<Integer, Tuple2<Iterable<String>, Iterable<Integer>>>>() {
            @Override
            public void call(Tuple2<Integer, Tuple2<Iterable<String>, Iterable<Integer>>> t) throws Exception {
                System.out.println("id:" + t._1);
                System.out.println("name:" + t._2._1);
                System.out.println("score:" + t._2._2);
                System.out.println("=======");
            }
        });
    }
}
