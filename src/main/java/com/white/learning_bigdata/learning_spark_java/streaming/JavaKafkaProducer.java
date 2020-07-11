package com.white.learning_bigdata.learning_spark_java.streaming;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.HashMap;

public class JavaKafkaProducer {
    public static void main(String[] args) throws Exception {
        HashMap<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "myspark:9092");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        KafkaProducer<String, String> producer = new KafkaProducer<>(props);
        while (true) {
            ProducerRecord<String, String> record = new ProducerRecord<>("test", null, "hello hello who are you");
            producer.send(record);
            Thread.sleep(5000);
        }

    }
}
