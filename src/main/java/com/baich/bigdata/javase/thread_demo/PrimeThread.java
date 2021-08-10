package com.baich.bigdata.javase.thread_demo;

/**
 * Created By IDEA.
 * Author : bai.chenghui
 * Date : Created in 2020-07-28
 * Time : 14:21
 * Description:
 * Modified By:
 * version : v1.0
 */
public class PrimeThread implements Runnable {
    long value;

    PrimeThread(long value) {
        this.value = value;
    }

    private boolean isPrime(long value) {
        boolean isPrime = true;
        if (value == 1) {
            isPrime = false;
        }
        for (int i = 2; i < value; i++) {
            if (value % i == 0) {
                isPrime = false;
                break;
            }
        }
        return isPrime;
    }

    @Override
    public void run() {
        boolean isPrime = false;
        long result = this.value;
        while (!isPrime) {
            isPrime = this.isPrime(result + 1);
            result += 1;
        }
        System.out.println("大于当前值的最小素数为：" + result);
    }

    public static void main(String[] args) {
//        new PrimeThread(5).run();
        Thread thread1 = new Thread(new PrimeThread(8));
        Thread thread2 = new Thread(new PrimeThread(12));
        thread1.start();
        thread2.start();
//        thread1.run();
//        thread2.run();
    }
}
