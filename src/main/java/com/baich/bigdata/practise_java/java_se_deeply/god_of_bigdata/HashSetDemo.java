package com.baich.bigdata.practise_java.java_se_deeply.god_of_bigdata;

import java.nio.ByteBuffer;
import java.util.HashSet;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2020-10-19
 * Time : 14:35
 * Description:
 * Modified By:
 * version : v1.0
 */
public class HashSetDemo {
    public static void main(String[] args) {
        HashSet<Integer> hashSet = new HashSet<>();
        hashSet.add(5);
        hashSet.add(7);
        for (Integer i : hashSet) {
            System.out.println(i);
        }
    }
}
