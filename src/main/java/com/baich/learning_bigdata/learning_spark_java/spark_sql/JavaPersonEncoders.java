package com.baich.learning_bigdata.learning_spark_java.spark_sql;

import org.apache.spark.SparkConf;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoder;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.SparkSession;

import java.util.Collections;

public class JavaPersonEncoders {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("jpe").setMaster("local");
        SparkSession session = SparkSession.builder().config(conf).getOrCreate();
        JavaPerson person = new JavaPerson();
        person.setName("Andy");
        person.setAge(32);
        Encoder<JavaPerson> personEncoder = Encoders.bean(JavaPerson.class);
        Dataset<JavaPerson> javaBeanDS = session.createDataset(Collections.singletonList(person), personEncoder);
        javaBeanDS.show();
    }
}
