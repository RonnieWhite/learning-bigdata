package com.baich.bigdata.design_patterns.singleton;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-07-18
 * Time : 17:52
 * Description:
 * Modified By:
 * version : v1.0
 */
public class LazySingleton5 {
    private static class SingletonHolder {
        private static LazySingleton5 instance = new LazySingleton5();
    }

    private LazySingleton5() {
    }

    public static LazySingleton5 getInstance() {
        return SingletonHolder.instance;
    }
}
