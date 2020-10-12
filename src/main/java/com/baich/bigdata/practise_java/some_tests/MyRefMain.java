package com.baich.bigdata.practise_java.some_tests;

import java.lang.reflect.Constructor;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2020-09-25
 * Time : 10:15
 * Description:
 * Modified By:
 * version : v1.0
 */
public class MyRefMain {
    public static void main(String[] args) throws Exception {
        Class<?> aClass = Class.forName("com.baich.bigdata.practise_java.some_tests.MyRef");
        Constructor<?> constructor = aClass.getConstructor();
        Object instance = constructor.newInstance();
    }
}
