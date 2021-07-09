package com.baich.bigdata.practise_java.thread_demo.mydemo;

import java.sql.Timestamp;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-07-03
 * Time : 09:21
 * Description:
 * Modified By:
 * version : v1.0
 */
public class MyTask extends Thread {
    private String taskName;

    public MyTask(String taskName) {
        this.taskName = taskName;
    }

    public boolean finished = false;

    @Override
    public void run() {
        System.out.println(String.format("%s：%s运行开始。", new Timestamp(System.currentTimeMillis()), taskName));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(String.format("%s：%s运行结束。", new Timestamp(System.currentTimeMillis()), taskName));
        this.finished = true;
    }
}
