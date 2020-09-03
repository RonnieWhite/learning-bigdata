package com.white.learning_bigdata.practise_java.optional_demo;

import com.white.learning_bigdata.practise_java.day16.Student;

import java.util.Optional;

/**
 * Created By IDEA.
 * Author : bai.chenghui
 * Date : Created in 2020-08-11
 * Time : 11:17
 * Description:
 * Modified By:
 * version : v1.0
 */
public class OptionalDemo {
    public String getName(Student student) {
//        return student.getName();
        return Optional.ofNullable(student).map(Student::getName).orElse("Unkown");
    }

    public static void main(String[] args) {
        OptionalDemo demo = new OptionalDemo();
        System.out.println(demo.getName(null));

    }
}
