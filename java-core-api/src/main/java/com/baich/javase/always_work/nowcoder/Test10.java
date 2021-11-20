package com.baich.javase.always_work.nowcoder;

import java.util.HashSet;
import java.util.Scanner;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-06-13
 * Time : 23:33
 * Description:编写一个函数，计算字符串中含有的不同字符的个数。字符在ASCII码范围内(0~127，包括0和127)，换行表示结束符，不算在字符里。不在范围内的不作统计。多个相同的字符只计算一次
 * 例如，对于字符串abaca而言，有a、b、c三种不同的字符，因此输出3。
 * Modified By:
 * version : v1.0
 */
public class Test10 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        int length = str.length();
        HashSet<Character> hashSet = new HashSet<>();
        for (int i = 0; i < length; i++) {
            hashSet.add(str.charAt(i));
        }
        System.out.println(hashSet.size());

    }
}
