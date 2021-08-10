package com.baich.bigdata.javase.thread_demo.thread_in_thread;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-07-03
 * Time : 07:42
 * Description:
 * Modified By:
 * version : v1.0
 */
public class Worker extends Thread {
    private String taskName;
    private int maxTimeout = 20;

    public Worker(String taskName) {
        this.taskName = taskName;
    }

    public void workerThread() throws InterruptedException {
        System.out.println("start " + taskName);
        Timestamp start = new Timestamp(System.currentTimeMillis());
        Timestamp stop = new Timestamp(start.getTime() + maxTimeout * 1000);
        SubWorker subWorker = new SubWorker(taskName);
        subWorker.start();
        do {
            Thread.sleep(2000);
            System.out.println(taskName + " work " + start.toString() + ", max: " + stop.toString());
        } while (!subWorker.finished && (new Timestamp(System.currentTimeMillis())).before(stop));
        System.out.println("worker id finished? " + subWorker.finished + "," + new Timestamp(new Date().getTime()).toString());
        if (!subWorker.finished) {
            System.out.println("work timeout ! interrupted");
            subWorker.stop();
        }
        System.out.println("stop " + taskName);
    }

    @Override
    public void run() {
        try {
            workerThread();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
