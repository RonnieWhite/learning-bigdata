package com.baich.bigdata.javase.some_tests;

import java.util.Date;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-06-17
 * Time : 15:10
 * Description:
 * Modified By:
 * version : v1.0
 */
public class TimeTest {
    public static void main(String[] args) throws InterruptedException {
        Inner thread1 = new Inner();
        Inner thread2 = new Inner();
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("Main thread" + new Date().toString());
    }

    static class Inner extends Thread {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getId() + new Date().toString());
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getId() + new Date().toString());
        }
    }
}
