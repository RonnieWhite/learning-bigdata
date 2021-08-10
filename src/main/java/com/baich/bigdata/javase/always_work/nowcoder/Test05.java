package com.baich.bigdata.javase.always_work.nowcoder;

import java.util.Scanner;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-06-13
 * Time : 09:51
 * Description: 计算字符串最后一个单词的长度，单词以空格隔开，字符串长度小于5000。
 * Modified By:
 * version : v1.0
 */
public class Test05 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        String[] words = str.split(" ");
        System.out.println(words[words.length - 1].length());
    }
}
