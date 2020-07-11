package com.white.learning_bigdata.practise_java;

public class testString {
    public static void main(String[] args) {
        String string = "hello";
        char[] chars = string.toCharArray();
        for (char c : chars) {
            System.out.println(c);
        }
    }
}
