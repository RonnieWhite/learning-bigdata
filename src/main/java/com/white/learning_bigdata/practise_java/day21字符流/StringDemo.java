package com.white.learning_bigdata.practise_java.day21字符流;

import org.junit.Test;

public class StringDemo {
    @Test
    public void encode() {
        String a = "跑啊";
        byte[] bytes = a.getBytes();
        String b = new String(bytes);
        System.out.println(b);
    }
}
