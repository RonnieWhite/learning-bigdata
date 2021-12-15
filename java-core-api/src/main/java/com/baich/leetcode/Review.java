package com.baich.leetcode;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-11-28
 * Time : 16:21
 * Description:
 * Modified By:
 * version : v1.0
 */
public class Review {
    public static void main(String[] args) {
        ListNode listNode = new ListNode(1, new ListNode(2, new ListNode(3, null)));
//        ListNode reverse = reverse(listNode);
//        ListNode reverse = reverse1(listNode);
//        System.out.println(reverse);
//        int i = countPrime(100);
//        System.out.println(i);
        System.out.println(getLength(new Integer[]{1, 3, 4, 4, 5}));

    }

    private static ListNode reverse(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    private static ListNode reverse1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode listNode = reverse1(head.next);
        head.next.next = head;
        head.next = null;
        return listNode;
    }

    static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    private static int countPrime(int range) {
        int count = 0;
        for (int i = 2; i < range; i++) {
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

    private static int getLength(Integer[] nums) {

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


