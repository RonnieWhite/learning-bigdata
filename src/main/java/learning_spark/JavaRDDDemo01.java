package main.java.learning_spark;

import org.apache.flink.api.java.tuple.Tuple;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.rdd.RDD;
import org.apache.spark.util.LongAccumulator;
import scala.Tuple2;

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
        wc(sc);
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
        Broadcast<Integer> broadcast = sc.broadcast(1);
        System.out.println(broadcast.value());
    }

    public static long accumDemo(JavaSparkContext sc) {
        LongAccumulator accum = sc.sc().longAccumulator();
        return accum.value();
    }

    public static void wc(JavaSparkContext sc) {
        JavaRDD<String> rdd = sc.textFile("E:/data/spark/README.md");
        JavaPairRDD<String, Integer> pair = rdd.mapToPair(s -> new Tuple2<>(s, 1));
        JavaPairRDD<String, Integer> counts = pair.reduceByKey((a, b) -> a + b);
        System.out.println(counts);
    }


}
