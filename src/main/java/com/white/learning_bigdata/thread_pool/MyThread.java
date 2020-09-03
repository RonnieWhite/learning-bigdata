package com.white.learning_bigdata.thread_pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created By IDEA.
 * Author : bai.chenghui
 * Date : Created in 2020-08-31
 * Time : 17:56
 * Description:
 * Modified By:
 * version : v1.0
 */
public class MyThread {
    private static int POOL_NUM = 10;

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < POOL_NUM; i++) {
            RunnableThread thread = new RunnableThread();
            executorService.execute(thread);
            executorService.shutdown();
        }
    }
}
