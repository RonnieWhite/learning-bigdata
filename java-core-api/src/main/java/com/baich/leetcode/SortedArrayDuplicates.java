package com.baich.leetcode;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-12-04
 * Time : 10:10
 * Description: 删除排序数组中的重复项
 * 一个有序数组nums，原地删除重复出现的元素，使每个元素只出现一次，返回删除后数组的新长度
 * Modified By:
 * version : v1.0
 */
public class SortedArrayDuplicates {
    public static void main(String[] args) {
        System.out.println(removeDuplicates(new int[]{1, 1, 3, 5, 6, 7, 7}));

    }

    private static int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[i]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return i + 1;
    }
}
