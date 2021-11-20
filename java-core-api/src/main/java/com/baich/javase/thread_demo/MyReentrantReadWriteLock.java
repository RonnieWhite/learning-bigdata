package com.baich.javase.thread_demo;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2020-10-12
 * Time : 19:31
 * Description:
 * Modified By:
 * version : v1.0
 */
public class MyReentrantReadWriteLock implements Runnable {
    private static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    static int total = 0;

    @Override
    public void run() {
//        lock.readLock().lock();
        lock.writeLock().lock();
        try {
            Thread.sleep(200);
            total += 1;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
//            lock.readLock().unlock();
            lock.writeLock().unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyReentrantReadWriteLock myReentrantReadWriteLock = new MyReentrantReadWriteLock();
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(myReentrantReadWriteLock);
            thread.start();
        }
        Thread.sleep(8000);
        System.out.println(total);
    }
}
