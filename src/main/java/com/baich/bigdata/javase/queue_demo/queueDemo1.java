package com.baich.bigdata.javase.queue_demo;


import java.util.LinkedList;
import java.util.Queue;

public class queueDemo1 {
    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<>();
        queue.offer("a");
        queue.offer("b");
        queue.offer("c");
//        for (String q : queue) {
//            System.out.println(q);
//        }
        System.out.println(queue.poll());
        System.out.println("====");
        for (String q : queue) {
            System.out.println(q);
        }
        System.out.println("----");
        System.out.println(queue.element());
        System.out.println("+++++");
        System.out.println(queue.peek());
    }
}
