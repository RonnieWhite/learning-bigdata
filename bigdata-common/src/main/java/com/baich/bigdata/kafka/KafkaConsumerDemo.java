package com.baich.bigdata.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Properties;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-11-11
 * Time : 09:42
 * Description: 指定partition和offset进行消费
 * Modified By:
 * version : v1.0
 */
public class KafkaConsumerDemo {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "vm01:9092");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "test01");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
/*//        consumer.subscribe(Arrays.asList("test1"));
        consumer.assign(Arrays.asList(new TopicPartition("test1", 0)));
        // 手动指定从分区0 offset为47的记录开始消费
        consumer.seek(new TopicPartition("test1", 0), 47);
        // 指定从分区0的起始位置开始消费
        consumer.seekToBeginning(Arrays.asList(new TopicPartition("test1", 0)));*/
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(1000);
            for (ConsumerRecord<String, String> record : records) {
                System.out.println(record);
            }
        }
    }
}
