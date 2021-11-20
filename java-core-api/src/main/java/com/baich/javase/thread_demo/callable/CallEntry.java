package com.baich.javase.thread_demo.callable;

import java.sql.Timestamp;
import java.util.concurrent.*;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-07-04
 * Time : 08:19
 * Description:
 * Modified By:
 * version : v1.0
 */
public class CallEntry {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
/*        FutureTask<String> futureTask1 = new FutureTask<String>(new CallWorker());
        FutureTask<String> futureTask2 = new FutureTask<String>(new CallWorker());
        FutureTask<String> futureTask3 = new FutureTask<String>(new CallWorker());
        futureTask1.run();
        futureTask2.run();
        futureTask3.run();
        String s1 = futureTask1.get();
        String s2 = futureTask2.get();
        String s3 = futureTask3.get();
        System.err.println(s1);
        System.err.println(s2);
        System.err.println(s3);*/
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            Future future = executorService.submit(new CallWorker());
            Object o = future.get();
            sb.append(o.toString());
        }
        System.err.println(sb.toString());
        executorService.shutdown();
    }
}

class CallWorker implements Callable {

    @Override
    public String call() throws Exception {
        System.out.println(new Timestamp(System.currentTimeMillis()).toString() + ":" + Thread.currentThread().getName() + "开始运行。");
        Thread.sleep(2000);
        System.out.println(new Timestamp(System.currentTimeMillis()).toString() + ":" + Thread.currentThread().getName() + "运行结束。");
        return new Timestamp(System.currentTimeMillis()).toString() + " " + Thread.currentThread().getName() + " finished.";
    }
}
