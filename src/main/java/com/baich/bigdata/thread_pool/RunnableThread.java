package com.baich.bigdata.thread_pool;

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
    private int THREAD_NUM = 10;
    @Override
    public void run() {
        for (int i = 0; i < THREAD_NUM; i++) {
            System.out.println("线程" + Thread.currentThread() + " " + i);
        }
    }
}
