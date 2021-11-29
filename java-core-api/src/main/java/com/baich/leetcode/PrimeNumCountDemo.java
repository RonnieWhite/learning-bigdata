package com.baich.leetcode;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-11-28
 * Time : 11:10
 * Description: 素数个数统计：统计n以内的素数个数，比如：输入100，输出25
 * Modified By:
 * version : v1.0
 */
public class PrimeNumCountDemo {
    public static void main(String[] args) {
//        System.out.println(forceCount(100));
        System.out.println(eratosthenes(100));
    }

    // 暴力求解法
    private static int forceCount(int max) {
        if (max < 2) {
            return 1;
        }
        int count = 0;
        for (int i = 2; i < max; i++) {
            count += isPrime(i) ? 1 : 0;
        }
        return count;
    }

    private static boolean isPrime(int value) {
        for (int i = 2; i < value / 2 + 1; i++) {
            if (value % i == 0) {
                return false;
            }
        }
        return true;
    }

    // 埃式筛选法
    private static int eratosthenes(int n) {
        boolean[] isPrime = new boolean[n];
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (!isPrime[i]) {
                count++;
                for (int j = 2 * i; j < n; j += i) {
                    isPrime[j] = true;
                }
            }
        }
        return count;
    }
}
