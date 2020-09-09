package com.baich.bigdata.practise_java.some_tests;

import com.google.common.base.Stopwatch;

/**
 * Created By IDEA.
 * Author : bai.chenghui
 * Date : Created in 2020-08-21
 * Time : 14:05
 * Description:
 * Modified By:
 * version : v1.0
 */
public class StopWatchDemo {
    public static void main(String[] args) throws InterruptedException {
        Stopwatch stopwatch = new Stopwatch();
        Thread.sleep(5000);
        stopwatch.stop();
        long millis = stopwatch.elapsedMillis();
        System.out.println("time: " + millis);
    }
}
