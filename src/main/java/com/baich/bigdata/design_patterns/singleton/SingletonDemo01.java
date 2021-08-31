package com.baich.bigdata.design_patterns.singleton;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-08-23
 * Time : 10:53
 * Description:
 * Modified By:
 * version : v1.0
 */
public class SingletonDemo01 {
    private static volatile SingletonDemo01 INSTANCE;

    private SingletonDemo01() {
    }

    public static SingletonDemo01 getInstance() {
        if (INSTANCE == null) {
            synchronized (SingletonDemo01.class) {
                if (INSTANCE == null) {
                    INSTANCE = new SingletonDemo01();
                }
            }
        }
        return INSTANCE;
    }
}
