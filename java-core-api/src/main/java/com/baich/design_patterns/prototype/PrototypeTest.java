package com.baich.design_patterns.prototype;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-07-17
 * Time : 17:25
 * Description:
 * Modified By:
 * version : v1.0
 */
public class PrototypeTest {
    public static void main(String[] args) throws Exception {
        RealizeType obj1 = new RealizeType();
        RealizeType obj2 = (RealizeType) obj1.clone();
        System.out.println(obj1 == obj2);
        System.out.println(obj1.equals(obj2));
    }
}
