package com.baich.bigdata.design_patterns.singleton;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-07-18
 * Time : 17:38
 * Description: 线程不安全
 * Modified By:
 * version : v1.0
 */
public class LazySingleton1 {
    private static volatile LazySingleton1 instance = null;

    private LazySingleton1() {
    }

    public LazySingleton1 getInstance() {
        if (instance == null) {
            return new LazySingleton1();
        }
        return instance;
    }
}
