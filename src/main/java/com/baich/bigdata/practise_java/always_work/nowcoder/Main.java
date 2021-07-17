package com.baich.bigdata.practise_java.always_work.nowcoder;

import org.apache.flink.api.common.functions.RichFunction;

import java.util.StringJoiner;

/**
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
/*        Class<?> aClass = null;
        try {
            aClass = Class.forName("com.baich.Abc");
        } catch (ClassNotFoundException e) {
            //e.printStackTrace();
            System.out.println(e.toString());
//            System.exit(-1);
        }
        System.out.println(aClass);*/
/*        Hello hello = new Hello();
        hello.calc();
        System.out.println("exit");*/
        StringJoiner stringJoiner = new StringJoiner(",");
        stringJoiner.add("1");
        stringJoiner.add("2");
        System.out.println(stringJoiner.toString());
    }

    static class Hello {
        private void calc() {
            Thread a = new Thread(new Runnable() {
                @Override
                public void run() {
                    long j = 0;
                    for (int i = 0; i < Integer.MAX_VALUE; i++) {
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        j += i;
                    }
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(j);
                }
            });
            a.start();
            System.out.println("hello exit");
            try {
                //a.join();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

