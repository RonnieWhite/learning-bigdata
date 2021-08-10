package com.baich.bigdata.javase.thread_demo.thread_in_thread;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-07-03
 * Time : 07:40
 * Description:
 * Modified By:
 * version : v1.0
 */
public class Entry {
    public static void main(String[] args) {
        Thread worker = new Thread((Runnable) () -> {
            for (int i = 0; i < 5; i++) {
                try {
                    new Worker("worker_" + i).run();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        worker.start();
        System.out.println("Main waiting");
/*        try {
            work.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        System.out.println("Main exit");
    }
}
