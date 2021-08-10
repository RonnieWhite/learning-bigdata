package com.baich.bigdata.javase.always_work.nowcoder;

import java.util.Scanner;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-06-13
 * Time : 23:41
 * Description: * 输入一个整数，将这个整数以字符串的形式逆序输出
 *  * 程序不考虑负数的情况，若数字含有0，则逆序形式也含有0，如输入为100，则输出为001
 * Modified By:
 * version : v1.0
 */
public class Test11 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = String.valueOf(sc.nextInt());
        StringBuffer sb = new StringBuffer();
        for (int i = str.length() - 1; i >= 0; i--) {
            sb.append(str.charAt(i));
        }
        System.out.println(sb.toString());
    }
}
