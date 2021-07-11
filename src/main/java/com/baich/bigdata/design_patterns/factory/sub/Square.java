package com.baich.bigdata.design_patterns.factory.sub;

import com.baich.bigdata.design_patterns.factory.Shape;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-07-11
 * Time : 18:18
 * Description:
 * Modified By:
 * version : v1.0
 */
public class Square implements Shape {
    @Override
    public void draw() {
        System.out.println("Inside Square::draw() method.");
    }
}
