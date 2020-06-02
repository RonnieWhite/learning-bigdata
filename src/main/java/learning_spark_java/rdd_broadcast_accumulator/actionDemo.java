package learning_spark_java.rdd_broadcast_accumulator;


import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function2;

import java.util.Arrays;
import java.util.List;

public class actionDemo {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setMaster("local[1]").setAppName("ad");
        JavaSparkContext jsc = new JavaSparkContext(conf);
//        reduce(jsc);
        collect(jsc);
        jsc.close();
    }

    private static void reduce(JavaSparkContext jsc) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        JavaRDD<Integer> rdd = jsc.parallelize(list);
        Integer reduce = rdd.reduce(new Function2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer v1, Integer v2) throws Exception {
                return v1 + v2;
            }
        });
        System.out.println(reduce);
    }

    private static void collect(JavaSparkContext jsc) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        JavaRDD<Integer> rdd = jsc.parallelize(list);
        List<Integer> collect = rdd.collect();
        System.out.println(collect);
    }
}
