package com.baich.bigdata.javase.thread_demo;


/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2020-10-12
 * Time : 11:40
 * Description:
 * Modified By:
 * version : v1.0
 */
public class SynchronizedDemo implements Runnable {
//    static SynchronizedDemo synchronizedDemo = new SynchronizedDemo();

    @Override
    public void run() {
        // 1. 此类写法，必须声明一个类型为当前类的静态变量，
        // 以便在main方法中直接使用其生成一个新的线程，同样是对类对象进行上锁，这种做法显得比较蠢.
        /*
        synchronized (synchronizedDemo) {
            System.out.println(Thread.currentThread() + "start work.");
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread() + " finish.");
        }
         */

        // 2. 此类写法，定义一个synchronized修饰的方法printMsg(),并在run()中调用，
        // 同样是对类对象进行上锁，这种做法比较简便
        printMsg();
    }


    private synchronized void printMsg() {
        System.out.println(Thread.currentThread() + "start work.");
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread() + " finish.");
    }


    public static void main(String[] args) throws Exception {
        SynchronizedDemo synchronizedDemo = new SynchronizedDemo();
        Thread thread1 = new Thread(synchronizedDemo, "thread1");
        Thread thread2 = new Thread(synchronizedDemo, "thread2");
        thread1.start();
        thread2.start();
    }
}
