package com.baich.bigdata.javase.stream_demo;

import java.util.Arrays;

/**
 * @Author: Chenghui Bai
 * Date: 2020/10/23 16:06
 * project name: learning-bigdata
 * @PackgeName: com.baich.bigdata.practise_java.stream_demo
 * @ClassName: StreamTest
 * @Version:
 * @Description:
 */
public class StreamTest {
    public static void main(String[] args) {
        String[] arr = {"Jordan","Michael","China"};
        long count = Arrays.stream(arr).map(x -> x + "bbb").count();
        System.out.println(count);
    }
}
