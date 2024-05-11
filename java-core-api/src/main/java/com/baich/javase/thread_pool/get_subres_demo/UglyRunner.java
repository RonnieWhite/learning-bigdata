package com.baich.javase.thread_pool.get_subres_demo;

import java.util.Date;
import java.util.concurrent.Callable;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2023/10/22
 * Time : 9:20
 * Description:
 * Modified By:
 * version : v1.0
 */
public class UglyRunner implements Callable<String> {
    @Override
    public String call() throws Exception {
        String threadName = Thread.currentThread().getName();
        System.out.println(String.format("%s start to run: %s", threadName, new Date()));
        System.out.println(threadName + ": Please hold on, I'm running....");
        Thread.sleep(3000);
        System.out.println(String.format("%s stop running: %s", threadName, new Date()));

        return threadName + ": I'm out now";
    }
}
