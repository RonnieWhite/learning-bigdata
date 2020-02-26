package main.practise.day17.game;

import java.util.Scanner;

public class GuessNumber {
    private GuessNumber() {

    }

    public static void start() {
        int number = (int) (Math.random() * 100) + 1;
        int count = 0;
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("请输入数据（1-100）：");
            int guessNumber = sc.nextInt();
            count++;

            if (guessNumber > number) {
                System.out.println("你猜的数:" + guessNumber + "大了");
            } else if (guessNumber < number) {
                System.out.println("你猜的数:" + guessNumber + "小了");
            } else {
                System.out.println("你猜对了！");
                System.out.println("一共猜了" + count + "次。");
                break;
            }
        }
    }
}
