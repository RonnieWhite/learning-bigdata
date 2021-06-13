package com.baich.bigdata.practise_java.always_work.nowcoder;

import java.util.Scanner;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-06-13
 * Time : 16:39
 * Description: 写出一个程序，接受一个正浮点数值，输出该数值的近似整数值。
 * 如果小数点后数值大于等于5,向上取整；小于5，则向下取整。
 * Modified By:
 * version : v1.0
 */
public class Test07 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        Integer start = Integer.valueOf(str.split("\\.")[0].substring(0, 1), 10);
        int end = Integer.valueOf(str.split("\\.")[1].substring(0,1));
        if (end >= 5) {
            start += 1;
        }
        System.out.println(start);
    }
}
