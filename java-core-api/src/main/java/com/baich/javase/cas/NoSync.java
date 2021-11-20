package com.baich.javase.cas;

/**
 * Author: Chenghui Bai
 * Date: 2021/1/13 11:29
 * ProjectName: learning-bigdata
 * PackageName: com.baich.bigdata.practise_java.cas
 * ClassName: NoSync
 * Version:
 * Description:
 */
public class NoSync {
    public static int count = 0;

    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            new Thread(
                    new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(10);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            for (int j = 0; j < 100; j++) {
                                count++;
                            }
                        }
                    }
            ).start();
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("count=" + count);
    }
}
