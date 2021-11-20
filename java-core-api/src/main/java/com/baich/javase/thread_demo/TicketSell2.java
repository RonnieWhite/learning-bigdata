package com.baich.javase.thread_demo;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-07-03
 * Time : 12:04
 * Description:
 * Modified By:
 * version : v1.0
 */
public class TicketSell2 {


    public static void main(String[] args) {
        int num = 100;
        SellWindow window = new SellWindow(num);
        for (int i = 0; i < 10; i++) {
//            new Thread(new SellWindow(num)).start();
            new Thread(window).start();
        }
    }
}

class SellWindow implements Runnable {
    private int num;

    public SellWindow(int num) {
        this.num = num;
    }

    @Override
    public void run() {
        while (num > 0) {
            synchronized (this) {
                num--;
                System.out.println(String.format("窗口%s正在卖出第%d张票......", Thread.currentThread().getName(), num));
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
