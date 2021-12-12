package com.baich.jvm;

import java.lang.reflect.Field;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-12-08
 * Time : 11:22
 * Description:
 * Modified By:
 * version : v1.0
 * VM Args: -Xmx20M -XX:MaxDirectMemorySize=10M
 */
public class DirectMemoryOOM {
    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) throws Exception {
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe)unsafeField.get(null);

    }

    static class Unsafe{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
