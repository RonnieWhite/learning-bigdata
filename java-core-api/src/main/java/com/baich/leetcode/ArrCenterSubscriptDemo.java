package com.baich.leetcode;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-12-04
 * Time : 17:03
 * Description: 给定一个整数数组nums，编写一个能够返回数组“中心下标”的方法
 * 中心下标是数组的一个下标，其左侧所有元素相加的和等于右侧所有元素相加的和，
 * 如果数组不存在中心下标，返回-1，如果数组有多个中心下标，返回最靠近左边的哪一个。
 * Modified By:
 * version : v1.0
 */
public class ArrCenterSubscriptDemo {
    public static void main(String[] args) {
        System.out.println(getCenterSubscript(new int[]{1,0,1}));
    }

    private static int getCenterSubscript(int arr[]) {
        int length = arr.length;
        for (int i = 0; i < length; i++) {
            int left = 0;
            int right = 0;
            for (int j = 0; j < i; j++) {
                left += arr[j];
            }

            for (int j = i + 1; j < length; j++) {
                right += arr[j];
            }
            if (left == right) {
                return i;
            }
        }
        return -1;
    }
}
