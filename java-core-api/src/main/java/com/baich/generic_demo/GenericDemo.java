package com.baich.generic_demo;


/**
 * Created By IDEA.
 * Author : bai.chenghui
 * Date : Created in 2020-08-11
 * Time : 10:11
 * Description:
 * Modified By:
 * version : v1.0
 */
public class GenericDemo<T> {
    private T key;

    public GenericDemo(T key) {
        this.key = key;
    }

    public T getKey() {
        return key;
    }

    public static <T> T getKey1(){
        String a = "abc";
        T t = (T) a;
        return t;
    }
}
