package com.baich.javase.thread_pool.get_subres_demo;

import java.util.concurrent.*;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2023/10/22
 * Time : 10:36
 * Description:
 * Modified By:
 * version : v1.0
 */
public class WaitMultiSubRes2 {
    public static int nThreads = 3;

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        UglyRunner uglyRunner = new UglyRunner();
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        CompletionService<String> completionService = new ExecutorCompletionService<>(executorService);

        for (int i = 0; i < nThreads; i++) {
            completionService.submit(uglyRunner);
        }
        String result = "";
        for (int i = 0; i < nThreads; i++) {
            result = String.format("%s %s", result, completionService.take().get());
        }
        System.out.println(result);
        executorService.shutdown();

    }
}
