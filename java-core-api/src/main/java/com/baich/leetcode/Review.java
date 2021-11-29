package com.baich.leetcode;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-11-28
 * Time : 16:21
 * Description:
 * Modified By:
 * version : v1.0
 */
public class Review {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        new Thread(ticket).start();
        new Thread(ticket).start();
        new Thread(ticket).start();
    }

}

class Ticket implements Runnable {

    private int tick = 100;
    private Lock lock = new ReentrantLock();

    @Override
    public void run() {
        while (true) {
            lock.lock();
            try {
                if (tick > 0) {
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " 完成售票，余票为 " + --tick);
                }
            } finally {
                lock.unlock();
            }
        }
    }
}
