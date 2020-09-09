package com.baich.learning_bigdata.java_core_tech.io;

import java.util.stream.Stream;

/**
 * Created By IDEA.
 * Author : bai.chenghui
 * Date : Created in 2020-09-07
 * Time : 22:37
 * Description:
 * Modified By:
 * version : v1.0
 */
public class StreamDemo {

    public static void main(String[] args) {
        StreamDemo streamDemo = new StreamDemo();
        streamDemo.generateStream();
        System.out.println(streamDemo.generateStream());
    }

    public long generateStream() {
        // 无限流
//        Stream<String> echos = Stream.generate(() -> "Echo");
        Stream<Double> randoms = Stream.generate(Math::random);
//        echos.forEach(System.out::println);
        randoms.forEach(System.out::println);
        return randoms.count();
    }
}
