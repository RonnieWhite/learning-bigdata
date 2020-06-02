package learning_spark_java.rdd_broadcast_accumulator;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.broadcast.Broadcast;

import java.util.Arrays;
import java.util.List;

public class JavaBroadCastVariable {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("bcv").setMaster("local");
        JavaSparkContext sc = new JavaSparkContext(conf);
        // 在java中，创建共享变量就是调用SparkContext的broadcast()方法，
        // 获取的返回结果是Broadcast<T>类型
        final int factor = 3;
        final Broadcast<Integer> factorBroadcast = sc.broadcast(factor);

        Integer[] arr = new Integer[]{1, 2, 3, 4, 5};
        List<Integer> list = Arrays.asList(arr);
        JavaRDD<Integer> numbers = sc.parallelize(list);
        // 让集合中的每一个数字，都乘以外部定义的那个factor
        JavaRDD<Integer> multipleNumbers = numbers.map(new Function<Integer, Integer>() {
            @Override
            public Integer call(Integer integer) throws Exception {
                // 使用共享变量时，用.value()方法获取值
                int factor = factorBroadcast.value();
                return integer * factor;
            }
        });
        multipleNumbers.foreach(new VoidFunction<Integer>() {
            @Override
            public void call(Integer integer) throws Exception {
                System.out.println(integer);
            }
        });
    }
}
