package com.baich.javase.nowcoder;

import java.util.Stack;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-08-23
 * Time : 21:31
 * Description:
 * Modified By:
 * version : v1.0
 */
public class Solution {
    public ListNode ReverseList(ListNode head) {
        Stack<ListNode> stack = new Stack<>();
        while (head != null) {
            stack.push(head);
            head = head.next;
        }
        if (stack.isEmpty()) {
            return null;
        }
        ListNode node = stack.pop();
        ListNode dummy = node;
        while (!stack.isEmpty()) {
            ListNode tempNode = stack.pop();
            node.next = tempNode;
            node = node.next;
        }
        node.next = null;
        return dummy;
    }
}
