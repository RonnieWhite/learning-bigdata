package com.white.learning_bigdata.learning_spark_java.spark_sql;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.MapFunction;
import org.apache.spark.sql.*;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SparkSessionRDD2DataFrame {
    public static void main(String[] args) {
        // 创建普通的RDD
        SparkConf conf = new SparkConf().setMaster("local").setAppName("SparkSessionRDD2DataFrame");
        SparkSession spark = SparkSession.builder().config(conf).getOrCreate();
        /**
         JavaRDD<Person> personJavaRDD = spark.read().textFile("E:/data/spark/person.txt")
         .javaRDD()
         .map(line -> {
         String[] parts = line.split(",");
         Person person = new Person();
         person.setName(parts[0]);
         person.setAge(Integer.parseInt(parts[1].trim()));
         return person;
         });
         Dataset<Row> dataset = spark.createDataFrame(personJavaRDD, Person.class);
         dataset.show();
         */
        JavaRDD<String> peopleRDD = spark.sparkContext()
                .textFile("E:/data/spark/person.txt", 0)
                .toJavaRDD();
        String schemaString = "name age";
        List<StructField> fields = new ArrayList<>();
        for (String fieldName : schemaString.split(" ")) {
            StructField field = DataTypes.createStructField(fieldName, DataTypes.StringType, true);
            fields.add(field);
        }
        StructType schema = DataTypes.createStructType(fields);
        // Convert records of the RDD (people) to Rows
        JavaRDD<Row> rowRDD = peopleRDD.map((Function<String, Row>) record -> {
            String[] attributes = record.split(",");
            return RowFactory.create(attributes[0], attributes[1].trim());
        });

// Apply the schema to the RDD
        Dataset<Row> peopleDataFrame = spark.createDataFrame(rowRDD, schema);

// Creates a temporary view using the DataFrame
        peopleDataFrame.createOrReplaceTempView("people");

// SQL can be run over a temporary view created using DataFrames
        Dataset<Row> results = spark.sql("SELECT name FROM people");

// The results of SQL queries are DataFrames and support all the normal RDD operations
// The columns of a row in the result can be accessed by field index or by field name
        Dataset<String> namesDS = results.map(
                (MapFunction<Row, String>) row -> "Name: " + row.getString(0),
                Encoders.STRING());
        namesDS.show();
        spark.close();
    }

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

}
