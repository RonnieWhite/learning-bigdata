package com.baich.javase.thread_demo.thread_in_thread;

import java.util.Random;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-07-03
 * Time : 07:42
 * Description:
 * Modified By:
 * version : v1.0
 */
public class SubWorker extends Thread {
    private String taskName;

    public SubWorker(String name) {
        this.taskName = name;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public boolean finished;

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public void run() {
        System.out.println("SubWorker start!");
        doSomething();
        this.setFinished(true);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("SubWorker stop!");
    }

    private void doSomething() {
        Random rnd = new Random();
        for (int i = 0, n = rnd.nextInt(15) + 1; i < n; i++) {
            System.out.println("---------" + this.getTaskName() + "--------start " + i + " execute sql in (" + n + ")");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("--------" + this.getTaskName() + "--------stop " + i + " execute sql in (" + n + ")");
        }
    }
}
