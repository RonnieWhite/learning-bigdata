package com.baich.bigdata.design_patterns.factory.sub;

import com.baich.bigdata.design_patterns.factory.Shape;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-07-11
 * Time : 18:19
 * Description:
 * Modified By:
 * version : v1.0
 */
public class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Inside Circle::draw() method.");
    }
}
