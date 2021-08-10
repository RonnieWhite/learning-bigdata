package com.baich.bigdata.javase.always_work.nowcoder;

import java.util.Scanner;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-06-13
 * Time : 16:00
 * Description:功能:输入一个正整数，按照从小到大的顺序输出它的所有质因子（重复的也要列举）（如180的质因子为2 2 3 3 5 ）
 * 最后一个数后面也要有空格
 * Modified By:
 * version : v1.0
 */
public class Test06 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long value = sc.nextLong();
        for (long i = 2; i <= value; i++) {
            while (value % i == 0) {
                System.out.print(i + " ");
                value = value / i;
            }
        }
    }
}
