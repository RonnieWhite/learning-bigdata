package com.baich.learning_bigdata.learning_hadoop.cn.day04_thread;

import java.util.Random;

public class myThread extends Thread {
  private String filename;

  private myThread(String filename) {
    this.filename = filename;
  }

  @Override
  public void run() {
    Random random = new Random();
    System.out.println(filename + "已开始下载...");
    try {
      Thread.sleep(random.nextInt(200));
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println(filename + "已下载完成。");
  }

  public static void main(String[] args) {
    Thread thread1 = new myThread("1.avi");
    Thread thread2 = new myThread("2.avi");
    thread1.start();
    thread2.start();
  }
}
