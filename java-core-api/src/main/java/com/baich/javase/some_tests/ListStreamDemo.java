package com.baich.javase.some_tests;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created By IDEA.
 * Author : bai.chenghui
 * Date : Created in 2020-08-19
 * Time : 10:34
 * Description:
 * Modified By:
 * version : v1.0
 */
public class ListStreamDemo {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            list.add(i);
        }
        List<Integer> stream = list.stream().map(x -> x * 2).collect(Collectors.toList());
        for (Integer i : stream) {
            System.out.println(i);
        }
    }
}
