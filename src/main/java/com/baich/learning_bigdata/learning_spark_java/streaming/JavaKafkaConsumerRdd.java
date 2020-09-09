package com.baich.learning_bigdata.learning_spark_java.streaming;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.streaming.kafka010.KafkaUtils;
import org.apache.spark.streaming.kafka010.LocationStrategies;
import org.apache.spark.streaming.kafka010.OffsetRange;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class JavaKafkaConsumerRdd {
    public static void main(String[] args) throws Exception {
        SparkConf conf = new SparkConf().setAppName("jkp").setMaster("local[2]");
        JavaSparkContext jsc = new JavaSparkContext(conf);
        Map<String, Object> kafkaParams = new HashMap<>();
        kafkaParams.put("bootstrap.servers", "myspark:9092");
        kafkaParams.put("key.deserializer", StringDeserializer.class);
        kafkaParams.put("value.deserializer", StringDeserializer.class);
        kafkaParams.put("group.id", "use_a_separate_group_id_for_each_stream");
        kafkaParams.put("auto.offset.reset", "latest");
        kafkaParams.put("enable.auto.commit", false);
        Collection<String> topics = Arrays.asList("test");
        OffsetRange[] offsetRanges = {
                // topic, partition, inclusive starting offset, exclusive ending offset
                OffsetRange.create("test", 0, 0, 100),
                OffsetRange.create("test", 1, 0, 100)
        };
        JavaRDD<ConsumerRecord<Object, Object>> rdd = KafkaUtils.createRDD(
                jsc,
                kafkaParams,
                offsetRanges,
                LocationStrategies.PreferConsistent()
        );
        rdd.foreach(r -> System.out.println(r.value()));
    }
}
