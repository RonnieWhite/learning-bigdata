package com.baich.bigdata.design_patterns.singleton;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-07-17
 * Time : 17:03
 * Description: 懒汉式
 * Modified By:
 * version : v1.0
 */
public class LazySingleton {
    private static volatile LazySingleton instance = null; // 保证instance在所有线程中同步

    private LazySingleton() {
        // private 避免类在外部被实例化
    }

    public static synchronized LazySingleton getInstance() {
        // getInstance 方法前同步
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }
}
