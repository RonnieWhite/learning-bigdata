package com.baich.javase.thread_demo.ticket_demo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-11-28
 * Time : 21:17
 * Description:
 * Modified By:
 * version : v1.0
 */
public class TicketSellInLock {
    public static void main(String[] args) {
        Seller seller = new Seller(100);
        Thread thread1 = new Thread(seller);
        Thread thread2 = new Thread(seller);
        Thread thread3 = new Thread(seller);
        thread1.start();
        thread2.start();
        thread3.start();
    }

    static class Seller implements Runnable {
        Lock lock = new ReentrantLock();

        private Integer count;

        public Seller(Integer count) {
            this.count = count;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " 开始卖票。");
            while (count > 1) {
                lock.lock();
                try {
                    Thread.sleep(200);
                    System.out.println(Thread.currentThread().getName() + " 完成售票，余票" + (count--));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock.unlock();
            }
        }
    }
}
