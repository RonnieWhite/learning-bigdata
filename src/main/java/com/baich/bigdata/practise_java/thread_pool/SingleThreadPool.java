package com.baich.bigdata.practise_java.thread_pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created By IDEA.
 * Author : bai.chenghui
 * Date : Created in 2020-10-12
 * Time : 21:29
 * Description:
 * Modified By:
 * version : v1.0
 */
public class SingleThreadPool {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> System.out.println(Thread.currentThread().getName()));
        executor.shutdown();
    }
}
