package com.baich.javase.some_tests;

import java.util.UUID;

public class UUIDTest {
    public static void main(String[] args) {
        String uuid = UUID.randomUUID().toString();
        System.out.println(uuid);
    }
}
