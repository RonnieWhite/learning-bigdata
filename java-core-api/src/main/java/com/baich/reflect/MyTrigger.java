package com.baich.reflect;

import com.baich.exception.PrintDemo;

import java.lang.reflect.Method;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2022-03-28
 * Time : 21:24
 * Description:
 * Modified By:
 * version : v1.0
 */
public class MyTrigger {
    public static void main(String[] args) throws Exception {
        Method method = MyRunnable.class.getMethod("main", String[].class);
        method.invoke(null, (Object) args);

    }
}
