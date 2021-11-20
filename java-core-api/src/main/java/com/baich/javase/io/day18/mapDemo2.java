package com.baich.javase.io.day18;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class mapDemo2 {
    public static void main(String[] args) {
        Map<String, String> map = new TreeMap<>();
        map.put("No.1", "Bryant");
        map.put("No.2", "Jordan");
        map.put("No.3", "McGrady");
        Set<Map.Entry<String, String>> entries = map.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }

        System.out.println("+++++++");
    }

    public int factorial(int arg) {
        if (arg <= 1) {
            return 1;
        } else {
            return arg * factorial(arg - 1);
        }
    }
}
