package main.java.learning_spark.streaming;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.Optional;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaPairDStream;
import org.apache.spark.streaming.api.java.JavaReceiverInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import scala.Tuple2;

import java.util.ArrayList;
import java.util.List;

/**
 * 基于transform的实时黑名单过滤
 */
public class JavaTransformBlackList {
    public static void main(String[] args) throws Exception {
        SparkConf conf = new SparkConf().setAppName("jtb").setMaster("local[2]");
        JavaStreamingContext jsc = new JavaStreamingContext(conf, Durations.seconds(3));
        // 用户对我们的网站上的广告可以进行点击，
        // 点击之后，要进行实时计费，点一下，算一次钱
        // 对于那些帮助某些无良商家刷广告的人，建立一个黑名单。
        // 只要是黑名单中的用户点击的广告，就过滤掉
        // 这里的日志格式，就简化一下，用date username的方式
        // 先做一份模拟的黑名单RDD
        List<Tuple2<String, Boolean>> blacklist = new ArrayList<>();
        blacklist.add(new Tuple2<>("tom", true));
        JavaPairRDD<String, Boolean> blacklistRDD = jsc.sparkContext().parallelizePairs(blacklist);
        JavaReceiverInputDStream<String> adsClickLog = jsc.socketTextStream("localhost", 9999);
        JavaPairDStream<String, String> userAdsClickLogDStream = adsClickLog.mapToPair(line -> new Tuple2<>(line.split(" ")[1], line.split(" ")[0] + line.split(" ")[1]));
        // 然后就可以执行transform操作了，将每个batch的RDD，与黑名单RDD进行join、filter、map等操作
        // 实时进行黑名单过滤
        JavaDStream<String> validAdsClickLogStream = userAdsClickLogDStream.transform(new Function<JavaPairRDD<String, String>, JavaRDD<String>>() {
            @Override
            public JavaRDD<String> call(JavaPairRDD<String, String> userAdsClickLogRDD) throws Exception {
                // 并不是每个用户都存在于黑名单中，所以如果直接用join
                // 那么没有存在于黑名单中的数据，会无法join到。
                JavaPairRDD<String, Tuple2<String, Optional<Boolean>>> joinedRDD = (JavaPairRDD<String, Tuple2<String, Optional<Boolean>>>) userAdsClickLogRDD.leftOuterJoin(blacklistRDD);
                // 执行filter算子
                JavaPairRDD<String, Tuple2<String, Optional<Boolean>>> filteredRDD = joinedRDD.filter(new Function<Tuple2<String, Tuple2<String, Optional<Boolean>>>, Boolean>() {
                    @Override
                    public Boolean call(Tuple2<String, Tuple2<String, Optional<Boolean>>> v1) throws Exception {
                        if (v1._2._2().isPresent() && v1._2._2.get()) {
                            return false;
                        }
                        return true;
                    }
                });
                // 此时，filteredRDD中就只剩下没有被黑名单过滤的用户点击了
                // 进行map操作，转换成我们想要的格式
                return filteredRDD.map(s -> s._2._1);
            }
        });

        // 所以，要对输入的数据，进行转换操作，变成(username,date username)
        // 以便于后面对每个batch RDD与定义好的黑名单RDD进行join操作
        // 打印有效的广告点击日志
        validAdsClickLogStream.print();
        jsc.start();
        jsc.awaitTermination();
        jsc.close();
    }
}
