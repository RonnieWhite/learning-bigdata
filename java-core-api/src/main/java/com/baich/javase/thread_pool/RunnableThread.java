package com.baich.javase.thread_pool;

/**
 * Created By IDEA.
 * Author : bai.chenghui
 * Date : Created in 2020-08-31
 * Time : 18:01
 * Description:
 * Modified By:
 * version : v1.0
 */
public class RunnableThread implements Runnable {
    private String xxx;

    public RunnableThread(String xxx) {
        this.xxx = xxx;
    }

    @Override
    public void run() {
        System.out.println("===========");
        System.out.println(xxx);
        System.out.println(Thread.currentThread().getName());
        System.out.println("===========");
    }
}
