package com.baich.bigdata.design_patterns.prototype;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-07-17
 * Time : 17:23
 * Description:
 * Modified By:
 * version : v1.0
 */
public class RealizeType implements Cloneable {
    RealizeType(){
        System.out.println("原型创建成功");
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        System.out.println("原型复制成功");
        return (RealizeType)super.clone();
    }
}
