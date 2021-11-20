package com.baich.javase.thread_demo.mydemo.runnable;

import java.sql.Timestamp;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-07-03
 * Time : 11:18
 * Description:
 * Modified By:
 * version : v1.0
 */
public class MyRunnable {
    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            SubThread subThread = new SubThread();
            new Thread(subThread).start();
        }
    }

    static class SubThread implements Runnable {

        @Override
        public void run() {
            System.out.println(new Timestamp(System.currentTimeMillis()) + ": " + Thread.currentThread().getName() + " 开始执行。");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(new Timestamp(System.currentTimeMillis()) + ": " + Thread.currentThread().getName() + " 执行结束。");
        }
    }
}
