package com.baich.bigdata.javase.always_work.nowcoder;

import java.util.Scanner;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-06-10
 * Time : 22:32
 * Description:写出一个程序，接受一个十六进制的数，输出该数值的十进制表示。
 * Modified By:
 * version : v1.0
 */
public class Test04 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String hexStr = sc.nextLine();
            String value = Integer.valueOf(hexStr.substring(2), 16).toString();
            System.out.println(value);
        }
    }
}
