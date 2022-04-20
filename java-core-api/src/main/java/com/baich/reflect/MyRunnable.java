package com.baich.reflect;


import java.util.StringJoiner;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2022-03-28
 * Time : 21:25
 * Description:
 * Modified By:
 * version : v1.0
 */
public class MyRunnable {
    public static void main(String[] args) {
        StringJoiner joiner = new StringJoiner(" ");
        System.out.println(args[0]);
        for (String arg : args) {
            joiner.add(arg);
        }
        System.out.println(String.format("This is My Runnable...params include %s...", joiner));

    }
}
