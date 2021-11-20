package com.baich.javase.review.review_game;

import java.util.Scanner;

public class GuessNumber {
    public GuessNumber() {
    }

    public static void start() {
        int number = (int) (Math.random() * 100) + 1;
        int count = 0;
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("请输入一个1-100之间的整数：");
            int guessNumber = sc.nextInt();
            count++;
            if (guessNumber < number) {
                System.out.println("猜的数是" + guessNumber + "，小了！");
            } else if (guessNumber > number) {
                System.out.println("猜的数是" + guessNumber + "，大了！");
            } else {
                System.out.println("你猜对了！");
                System.out.println("你一共猜了" + count + "次。");
                break;
            }
        }
    }
}
