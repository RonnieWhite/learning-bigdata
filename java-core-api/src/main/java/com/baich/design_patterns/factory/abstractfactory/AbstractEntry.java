package com.baich.design_patterns.factory.abstractfactory;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-07-18
 * Time : 17:12
 * Description:
 * Modified By:
 * version : v1.0
 */
public class AbstractEntry {
    public static void main(String[] args) {
//        IFactory factory = new OrangeFactory();
        PearFactory factory = new PearFactory();
        Fruit fruit = factory.create();
        fruit.show();
    }
}
