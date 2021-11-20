package com.baich.javase.always_work.nowcoder;

import java.util.Scanner;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-06-10
 * Time : 20:14
 * Description:
 * 写出一个程序，接受一个由字母、数字和空格组成的字符串，和一个字母，然后输出输入字符串中该字母的出现次数。不区分大小写，字符串长度小于500。
 * Modified By:
 * version : v1.0
 */
public class Test01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        char c = sc.nextLine().charAt(0);
        char[] chars = str.toCharArray();
        int count = 0;
        for (char c1 : chars) {
            if (c1 == c) {
                count += 1;
            }
        }
        System.out.println(count);
    }
}
