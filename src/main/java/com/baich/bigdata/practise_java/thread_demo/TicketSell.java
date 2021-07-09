package com.baich.bigdata.practise_java.thread_demo;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-07-03
 * Time : 11:41
 * Description:
 * Modified By:
 * version : v1.0
 */
public class TicketSell {
    public static void main(String[] args) {
        int ticketAmount = 100;
        SubWindow subWindow = new SubWindow(ticketAmount);
        for (int i = 0; i < 5; i++) {
            subWindow.start();
        }


    }
}

class SubWindow extends Thread {
    private int ticketAmount;

    public SubWindow(int ticketAmount) {
        this.ticketAmount = ticketAmount;
    }

    @Override
    public void run() {
        while (ticketAmount > 0) {
            synchronized (this) {
                ticketAmount--;
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "已售出第" + (ticketAmount + 1) + "张票。");
        }
    }
}