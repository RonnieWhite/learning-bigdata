package com.baich.javase.thread_demo.mydemo;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-07-03
 * Time : 09:20
 * Description:
 * Modified By:
 * version : v1.0
 */
public class MyEntry {
    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            MyTask task = new MyTask("task00" + (i + 1));
            task.start();
/*            try {
                task.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
        }

    }

}
