package com.baich.design_patterns.singleton;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-07-18
 * Time : 17:44
 * Description: 双检锁方式，先判断，为null时再做同步操作，判断为null之后新建对象
 * Modified By:
 * version : v1.0
 */
public class LazySingleton4 {
    private static volatile LazySingleton4 instance = null;

    private LazySingleton4() {
    }

    public LazySingleton4 getInstance() {
        if (instance == null) {
            synchronized (LazySingleton4.class) {
                if (instance == null)
                    return new LazySingleton4();
            }
        }
        return instance;
    }
}
