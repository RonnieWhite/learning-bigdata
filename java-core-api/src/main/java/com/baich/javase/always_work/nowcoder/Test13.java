package com.baich.javase.always_work.nowcoder;

import java.util.Scanner;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-06-13
 * Time : 23:56
 * Description:* 将一个英文语句以单词为单位逆序排放。例如“I am a boy”，逆序排放后为“boy a am I”
 *  * 所有单词之间用一个空格隔开，语句中除了英文字母外，不再包含其他字符
 * Modified By:
 * version : v1.0
 */
public class Test13 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] strArr = sc.nextLine().split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = strArr.length - 1; i >= 0; i--) {
            if (i == 0) {
                sb.append(strArr[i]);
            } else {
                sb.append(strArr[i]).append(" ");
            }
        }
        System.out.println(sb);
    }
}
