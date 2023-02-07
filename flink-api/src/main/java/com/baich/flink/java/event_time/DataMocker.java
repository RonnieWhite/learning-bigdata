package com.baich.flink.java.event_time;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import java.util.StringJoiner;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2023-02-07
 * Time : 12:31
 * Description:
 * Modified By:
 * version : v1.0
 */
public class DataMocker {
    public static void main(String[] args) throws InterruptedException {
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "vm01:9092");
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");

        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(properties);
        // flowId,employeeName,income,flowTime
        String[] employeeNameList = {"tom", "bob", "andy", "jonas"};
        long timeBase = 1675656050L;
        for (int i = 1; i <= 100; i++) {
            for (int j = 0; j < 4; j++) {
                String employeeName = employeeNameList[j];
                float income = 1;
                long flowTime = timeBase + i;
                StringJoiner stringJoiner = new StringJoiner(",");
                StringJoiner joiner = stringJoiner.add(String.valueOf(i)).add(employeeName).add(String.valueOf(income)).add(String.valueOf(flowTime));
                ProducerRecord<String, String> producerRecord = new ProducerRecord<String, String>("employee", joiner.toString());
                kafkaProducer.send(producerRecord);
                Thread.sleep(1000);
            }
//            String employeeName = employeeNameList[new Random().nextInt(3)];
//            String employeeName = "tom";
//            float income = (float) (Math.random() * 1000 + 5000);

        }
        kafkaProducer.close();

    }
}
