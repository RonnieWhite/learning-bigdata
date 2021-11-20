package com.baich.review;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-07-18
 * Time : 17:20
 * Description:
 * Modified By:
 * version : v1.0
 */
public class Singleton {
    private static Singleton instance = null;

    private Singleton() {

    }

    public static Singleton getInstance() {
        synchronized (Singleton.class) {
            if (instance == null) {
                return new Singleton();
            }
            return instance;
        }
    }


}
