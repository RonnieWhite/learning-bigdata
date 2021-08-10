package com.baich.bigdata.javase.thread_pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2020-10-13
 * Time : 10:21
 * Description: 演示提供缓存功能的线程池：理论上为无限大的线程池，当执行当前任务时上一个任务已经完成，会复用执行上一个任务的线程，而不用每次新建线程
 * Modified By:
 * version : v1.0
 */
public class CachedThreadPool {
    public static void main(String[] args) throws Exception {
        ExecutorService pool = Executors.newCachedThreadPool();
        Runnable r1 = () -> {
            try {
                Thread.sleep(2000);
                System.out.println("这个线程");
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
//        pool.execute(r1);
        Future submit = pool.submit(r1);
        System.out.println(submit.get());
        pool.shutdown();
    }
}
