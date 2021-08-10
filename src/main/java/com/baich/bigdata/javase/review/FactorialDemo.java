package com.baich.bigdata.javase.review;

public class FactorialDemo {
    public static void main(String[] args) {
        int res = factorial(3);
        System.out.println(res);
    }

    private static int factorial(int x) {
        if (x <= 1) {
            return 1;
        } else {
            return x * factorial(x - 1);
        }
    }
}
