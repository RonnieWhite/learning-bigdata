package com.baich.javase.thread_pool.get_subres_demo;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2023/10/22
 * Time : 9:19
 * Description:
 * Modified By:
 * version : v1.0
 */
public class WaitMultiSubRes {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        UglyRunner uglyRunner = new UglyRunner();
        for (int i = 0; i < 3; i++) {
            FutureTask<String> futureTask = new FutureTask<>(uglyRunner);
            executor.submit(futureTask);
            while (!futureTask.isDone()) {
                System.out.println(Thread.currentThread().getName() + ": the sub task is still running....");
                Thread.sleep(1000);
            }
            System.out.println(futureTask.get());
        }
        executor.shutdown();
    }
}
