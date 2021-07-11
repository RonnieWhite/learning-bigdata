package com.baich.bigdata.design_patterns.factory;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-07-11
 * Time : 18:26
 * Description:
 * Modified By:
 * version : v1.0
 */
public class FactoryPatternDemo {
    public static void main(String[] args) {
        ShapeFactory factory = new ShapeFactory();
        Shape circle = factory.getShape("circle");
        circle.draw();
    }
}
