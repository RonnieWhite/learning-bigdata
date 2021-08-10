package com.baich.bigdata.javase.thread_pool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created By IDEA.
 * Author : bai.chenghui
 * Date : Created in 2020-10-12
 * Time : 21:36
 * Description:
 * Modified By:
 * version : v1.0
 */
public class ScheduledThreadPool {
    public static void main(String[] args) {
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(5);
        Runnable r1 = () -> System.out.println("线程名称：" + Thread.currentThread().getName() + "，执行：3秒后执行");
        pool.schedule(r1, 3, TimeUnit.SECONDS);
        Runnable r2 = () -> System.out.println("线程名称：" + Thread.currentThread().getName() + "，执行：延迟2秒后每3秒执行一次");
        pool.scheduleAtFixedRate(r2, 2, 3, TimeUnit.SECONDS);
        Runnable r3 = () -> System.out.println("线程名称：" + Thread.currentThread().getName() + "，执行：普通任务");
        for (int i = 0; i < 5; i++) {
            pool.execute(r3);
        }
    }
}
