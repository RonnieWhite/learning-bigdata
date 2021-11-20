package com.baich.javase.thread_pool;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-06-29
 * Time : 19:10
 * Description:
 * Modified By:
 * version : v1.0
 */
public class MyThreadPool02 {
    public static void main(String[] args) {
/*        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 5; i++) {
            String uuid = UUID.randomUUID().toString();
            MyRunnable runnable = new MyRunnable(uuid);
            Future<?> future = executorService.submit(runnable);
            try {
                future.get(3000, TimeUnit.MILLISECONDS);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        executorService.shutdown();
        System.out.println(System.currentTimeMillis() + " Finish.");*/

/*        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        Date date = new Date();
        System.out.println(String.format("开始时间：%s", date.toString()));*/
/*        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(String.format("Now, %s", timestamp));*/
        boolean before = new Date().before(new Timestamp(System.currentTimeMillis() + 500000));
        System.out.println(before);
    }

    private static class MyRunnable implements Runnable {
        private String uuid;

        private MyRunnable(String uuid) {
            this.uuid = uuid;
        }

        @Override
        public void run() {
            System.out.println(System.currentTimeMillis() + " " + Thread.currentThread().getName() + " start===" + uuid);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(System.currentTimeMillis() + " " + Thread.currentThread().getName() + " end===" + uuid);
        }
    }
}
