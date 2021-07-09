package com.baich.bigdata.practise_java.thread_demo.mydemo;

import java.sql.Timestamp;
import java.util.concurrent.atomic.AtomicInteger;

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
