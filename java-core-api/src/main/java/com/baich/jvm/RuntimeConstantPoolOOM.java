package com.baich.jvm;

import java.util.HashSet;
import java.util.Set;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-12-08
 * Time : 10:35
 * Description:
 * Modified By:
 * version : v1.0
 * VM Args: -XX:PermSize=6M -XX:MaxPermSize=6M
 * JDK6及以下有效，从7开始，原本存放在永久代的字符常量池被移至Java堆中，所以限制方法取得容量对本测试用例毫无意义
 */
public class RuntimeConstantPoolOOM {
    public static void main(String[] args) {
        // 使用Set保持着常量池引用，避免Full GC回收常量池行为
        Set<String> set = new HashSet<>();
        short i = 0;
        while (true) {
            set.add(String.valueOf(i++).intern());
        }
    }
}
