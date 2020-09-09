package com.baich.learning_bigdata.practise_java.java_se_deeply;

import java.time.LocalDate;

/**
 * Created By IDEA.
 * Author : bai.chenghui
 * Date : Created in 2020-09-03
 * Time : 14:41
 * Description:
 * Modified By:
 * version : v1.0
 */
public class Demo02 {
    static {
        String hello = "Hello!";
        System.out.println(hello);
    }

    public Demo02() {
        System.out.println("构造函数");
    }

    public void switchMe(int i) {
        switch (i) {
            case 1:
                System.out.println("too small");
                break;
            case 2:
                System.out.println("nearly");
                break;
            case 3:
                System.out.println("bingo");
                break;
            case 4:
                System.out.println("too big");
                break;
            default:
                System.out.println("error");
                break;
        }
    }

    public static void main(String[] args) {
//        Demo02 demo02 = new Demo02();
//        Scanner sc = new Scanner(System.in);
//        System.out.println("Type a number");
//        int i = sc.nextInt();
//        demo02.switchMe(i);
//        Calendar instance = Calendar.getInstance();
//        System.out.println(instance.getTime());
        LocalDate localDate = LocalDate.now();
//        Date date = new Date();
        System.out.println(localDate);
        System.out.println(localDate.getChronology());
        System.out.println(localDate.getEra());
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        String format = simpleDateFormat.format(new Date());
//        System.out.println(format);
        MathDemo mathDemo = new MathDemo();
        System.out.println(mathDemo.toString());
    }
}
