package main.java.learning_spark.spark_sql;

import org.apache.spark.SparkContext;

import java.io.Serializable;


public class JavaPerson implements Serializable {
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
}
