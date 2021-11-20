package com.baich.design_patterns.singleton;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-07-18
 * Time : 17:41
 * Description: 先同步，再做的判断，效率会降低
 * Modified By:
 * version : v1.0
 */
public class LazySingleton3 {
    private static volatile LazySingleton3 instance = null;

    private LazySingleton3() {
    }

    public LazySingleton3 getInstance() {
        synchronized (LazySingleton3.class) {
            if (instance == null) {
                return new LazySingleton3();
            }
            return instance;
        }
    }
}
