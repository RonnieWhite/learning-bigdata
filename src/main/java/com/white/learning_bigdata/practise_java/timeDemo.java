package com.white.learning_bigdata.practise_java;


import java.sql.Timestamp;

public class timeDemo {
    public static void main(String[] args) {
        Timestamp ctime = new Timestamp(new java.util.Date().getTime());
        System.out.println(ctime);
    }
}
