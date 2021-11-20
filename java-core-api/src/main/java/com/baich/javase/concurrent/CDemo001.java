package com.baich.javase.concurrent;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-08-22
 * Time : 10:11
 * Description:
 * Modified By:
 * version : v1.0
 */
public class CDemo001 {
    private static AtomicInteger m = new AtomicInteger();

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[100];
        CountDownLatch latch = new CountDownLatch(threads.length);
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    m.incrementAndGet();
                }
                latch.countDown();
            });
        }

        Arrays.stream(threads).forEach(Thread::start);
        latch.await();
        System.out.println(m);
    }
}
