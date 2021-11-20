package com.baich.javase.thread_demo.custom_timer;

import java.util.Calendar;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-11-17
 * Time : 09:19
 * Description:
 * Modified By:
 * version : v1.0
 */
public class CustomTimerDemo {
    public static void main(String[] args) {
        CustomTimerDemo customTimerDemo = new CustomTimerDemo();
        customTimerDemo.registerTimer();
    }




    private void registerTimer() {
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        long delay = genDelayTimeInMillis();
        executor.scheduleAtFixedRate(new TimerRunnable(), delay, 24 * 60 * 60 * 1000, TimeUnit.MILLISECONDS);

    }


    static class TimerRunnable implements Runnable {

        @Override
        public void run() {
            System.out.println("Some thing...");
        }
    }

    private static long genDelayTimeInMillis() {
        Calendar calendar = Calendar.getInstance();
        long nowInMillis = calendar.getTimeInMillis();
        calendar.set(Calendar.HOUR_OF_DAY, 5);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        long wakeUpClockInMillis = calendar.getTimeInMillis();
        if (nowInMillis < wakeUpClockInMillis) {
            return wakeUpClockInMillis - nowInMillis;
        } else {
            return wakeUpClockInMillis + (24 * 60 * 60 * 1000) - nowInMillis;
        }
    }
}
