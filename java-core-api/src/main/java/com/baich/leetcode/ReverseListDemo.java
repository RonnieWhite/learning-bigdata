package com.baich.leetcode;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-11-28
 * Time : 09:04
 * Description: 反转链表
 * Modified By:
 * version : v1.0
 */
public class ReverseListDemo {

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1, null);
        ListNode node2 = new ListNode(2, node1);
        ListNode node3 = new ListNode(3, node2);
        Solution solution = new Solution();
//        ListNode reverseList = solution.reverseListByIteration(node3);
        ListNode reverseList = solution.reverseListByRecursion(node3);
        System.out.println(reverseList);
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    static class Solution {
        /**
         * 迭代
         *
         * @param head 链表
         * @return 反转后的链表
         */
        ListNode reverseListByIteration(ListNode head) {
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

        /**
         * 递归
         *
         * @param head 链表
         * @return 反转后的链表
         */
        ListNode reverseListByRecursion(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            ListNode newNode = reverseListByRecursion(head.next);

            head.next.next = head;
            head.next = null;

            return newNode;
        }

    }
}
