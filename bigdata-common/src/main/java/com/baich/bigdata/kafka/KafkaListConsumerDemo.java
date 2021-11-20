package com.baich.bigdata.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-11-11
 * Time : 09:35
 * Description:
 * Modified By:
 * version : v1.0
 */
public class KafkaListConsumerDemo {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "vm01:9092");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
        Map<String, List<PartitionInfo>> topics = consumer.listTopics();
        Set<Map.Entry<String, List<PartitionInfo>>> entries = topics.entrySet();
        for (Map.Entry<String, List<PartitionInfo>> entry : entries) {
            System.out.println(entry.getKey() + entry.getValue());
        }
    }
}
