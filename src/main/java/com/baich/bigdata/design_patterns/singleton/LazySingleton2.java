package com.baich.bigdata.design_patterns.singleton;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-07-17
 * Time : 17:03
 * Description: 懒汉式-直接在方法上进行同步，效率会降低
 * Modified By:
 * version : v1.0
 */
public class LazySingleton2 {
    private static volatile LazySingleton2 instance = null; // 保证instance在所有线程中同步

    private LazySingleton2() {
        // private 避免类在外部被实例化
    }

    public static synchronized LazySingleton2 getInstance() {
        // getInstance 方法前同步
        if (instance == null) {
            return new LazySingleton2();
        }
        return instance;
    }
}
