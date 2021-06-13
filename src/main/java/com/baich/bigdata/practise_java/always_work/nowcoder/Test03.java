package com.baich.bigdata.practise_java.always_work.nowcoder;

import java.util.Scanner;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-06-10
 * Time : 21:33
 * Description:•连续输入字符串，按长度为8拆分每个字符串后输出到新的字符串数组；•长度不是8整数倍的字符串请在后面补数字0，空字符串不处理。
 * Modified By:
 * version : v1.0
 */
public class Test03 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String str = sc.nextLine();
            while (str.length() > 8) {
                System.out.println(str.substring(0, 8));
                str = str.substring(8);
            }
            if (str.length() == 8) {
                System.out.println(str);
            }
            if (str.length() < 8) {
                StringBuilder sb = new StringBuilder();
                for (int i = str.length(); i < 8; i++) {
                    sb.append("0");
                }
                System.out.println(str.concat(sb.toString()));
            }
        }
    }
}
