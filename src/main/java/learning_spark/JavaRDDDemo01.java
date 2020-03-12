package main.java.learning_spark;

import org.apache.flink.api.java.tuple.Tuple;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.rdd.RDD;
import org.apache.spark.storage.StorageLevel;
import org.apache.spark.util.LongAccumulator;
import scala.Tuple2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class JavaRDDDemo01 {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("JavaRDDDemo01").setMaster("local[2]");
        JavaSparkContext sc = new JavaSparkContext(conf);
//        List<Integer> data = Arrays.asList(1, 2, 3, 4, 5);
//        JavaRDD<Integer> rdd = sc.parallelize(data);
//        List<Integer> list = rdd.collect();
//        for (int i : list) {
//            System.out.println(i);
//        }
//        readText(sc);
//        broadcastDemo(sc);
//        accumDemo(sc);
//        wc(sc);
//        accumDemo(sc);
        accumDemo2(sc);
//        reduceDemo(sc);
        sc.close();
    }

    public static void readText(JavaSparkContext sc) {
        JavaRDD<String> rdd = sc.textFile("hdfs://myspark:9000/spark/README.md");
        long count = rdd.filter(new Function<String, Boolean>() {
            @Override
            public Boolean call(String s) throws Exception {
                return s.contains("spark");
            }
        }).count();
        System.out.println(count);
    }

    public static void broadcastDemo(JavaSparkContext sc) {
        Broadcast<Long> broadcast = sc.broadcast(1L);
        System.out.println(broadcast.value());
    }

    public static void accumDemo(JavaSparkContext sc) {
        LongAccumulator accum = sc.sc().longAccumulator("he");
        JavaRDD<String> rdd = sc.textFile("E:/data/spark/README.md");
        long count = rdd.filter(new Function<String, Boolean>() {
            @Override
            public Boolean call(String s) throws Exception {
                return s.contains("spark");
            }
        }).count();
        System.out.println(accum.value() + count);
    }

    public static void wc(JavaSparkContext sc) {
        JavaRDD<String> rdd = sc.textFile("E:/data/spark/README.md");
        /**
         JavaPairRDD<String, Integer> pair = rdd.mapToPair(s -> new Tuple2<>(s, 1));
         pair.persist(StorageLevel.MEMORY_ONLY());
         JavaPairRDD<String, Integer> counts = pair.reduceByKey((a, b) -> a + b);
         List<Tuple2<String, Integer>> collect = counts.collect();
         System.out.println(collect);
         */
        JavaPairRDD<String, Integer> rdd1 = rdd.flatMap(new FlatMapFunction<String, String>() {
            @Override
            public Iterator<String> call(String s) throws Exception {
                ArrayList<String> list = new ArrayList<>();
                String[] arr = s.split(" ");
                for (String word : arr) {
                    list.add(word);
                }
                return list.iterator();
            }
        }).mapToPair(new PairFunction<String, String, Integer>() {
            @Override
            public Tuple2<String, Integer> call(String s) throws Exception {
                return new Tuple2<String, Integer>(s, 1);
            }
        }).reduceByKey(new Function2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer integer, Integer integer2) throws Exception {
                return integer + integer2;
            }
        });
        System.out.println(rdd1.collect());


    }

    public static void reduceDemo(JavaSparkContext sc) {
        JavaRDD<String> rdd = sc.textFile("E:/data/spark/README.md");
        Integer reduce = rdd.map(x -> x.length()).reduce((a, b) -> a + b);
        System.out.println(reduce);
    }

    public static void accumDemo2(JavaSparkContext sc) {
        LongAccumulator myCounter = sc.sc().longAccumulator("myCounter");
        int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        List<ArrayList<Integer>> count = sc.parallelize(Arrays.asList(arr)).map(s -> {
            ArrayList<Integer> list = new ArrayList<>();
            for (int i : s) {
                if (i % 2 == 0) {
                    list.add(i);
                }
            }
            return list;
        }).collect();
        System.out.println(count);
    }
}