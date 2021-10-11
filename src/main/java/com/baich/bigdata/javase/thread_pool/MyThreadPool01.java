package com.baich.bigdata.javase.thread_pool;

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
public class MyThreadPool01 {
    private static int POOL_NUM = 10;

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(POOL_NUM);
//        Executors.newCachedThreadPool();

        for (int i = 0; i < 20; i++) {
            String name = "xxx" + i;
            RunnableThread thread = new RunnableThread(name);
            executorService.execute(thread);
        }
        executorService.shutdown();
    }
}
