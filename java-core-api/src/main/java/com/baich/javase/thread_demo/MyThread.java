package com.baich.javase.thread_demo;

/**
 * Created By IDEA.
 * Author : bai.chenghui
 * Date : Created in 2020-07-28
 * Time : 14:06
 * Description:
 * Modified By:
 * version : v1.0
 */
public class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        new MyThread().start();
    }
}
