package com.baich.javase.some_tests;


import java.sql.Timestamp;

public class timeDemo {
    public static void main(String[] args) {
        Timestamp ctime = new Timestamp(new java.util.Date().getTime());
        System.out.println(ctime);
    }
}
