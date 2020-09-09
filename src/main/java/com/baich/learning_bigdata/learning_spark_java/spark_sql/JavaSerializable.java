package com.baich.learning_bigdata.learning_spark_java.spark_sql;

import org.apache.spark.SparkConf;
import org.apache.spark.sql.*;

import java.io.Serializable;

public class JavaSerializable {
    public static class Person implements Serializable {
        private String name;
        private int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public Person() {
        }
    }

    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("abc").setMaster("local[2]");
        SparkSession spark = SparkSession.builder().config(conf).getOrCreate();
        /**
         Person person1 = new Person();
         Person person2 = new Person();
         person1.setName("Andy");
         person1.setAge(32);
         person2.setName("Jojo");
         person2.setAge(30);
         List<Person> peopleList = new ArrayList<>();
         peopleList.add(person1);
         peopleList.add(person2);
         // Encoders are created for java beans
         Encoder<Person> personEncoder = Encoders.bean(Person.class);
         Dataset<Person> javaBeanDS = spark.createDataset(peopleList, personEncoder);
         javaBeanDS.show();
         */
        Encoder<Person> personEncoder = Encoders.bean(Person.class);
        Dataset<Person> dataset = spark.read().json("E:/data/spark/person.json").as(personEncoder);
        dataset.show();
        spark.close();
    }
}
