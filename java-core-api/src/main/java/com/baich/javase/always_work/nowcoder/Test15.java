package com.baich.javase.always_work.nowcoder;

import java.util.Scanner;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-06-14
 * Time : 00:28
 * Description: 输入一个int型的正整数，计算出该int型数据在内存中存储时1的个数。
 * Modified By:
 * version : v1.0
 */
public class Test15 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int value = sc.nextInt();
        String str = Integer.toBinaryString(value);
        char[] chars = str.toCharArray();
        int count = 0;
        for (char c:chars){
            if (c=='1'){
                count +=1;
            }
        }
        System.out.println(count);

    }
}
