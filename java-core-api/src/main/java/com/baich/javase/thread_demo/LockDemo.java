package com.baich.javase.thread_demo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2020-10-12
 * Time : 19:26
 * Description: Lock的测试，如果不加锁，多个线程将同时进行total += 1;操作，结果是错误的
 * Modified By:
 * version : v1.0
 */
public class LockDemo implements Runnable {
    static int total = 0;
    private Lock lock = new ReentrantLock();

    @Override
    public void run() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "开始");
            Thread.sleep(200);
            total += 1;
            System.out.println(Thread.currentThread().getName() + "结束");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws Exception {
        LockDemo lockDemo = new LockDemo();
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(lockDemo);
            thread.start();
        }
        Thread.sleep(8000);
        System.out.println(total);
    }
}
