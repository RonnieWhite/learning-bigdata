package com.baich.bigdata.design_patterns.duck.quack.impl;

import com.baich.bigdata.design_patterns.duck.quack.QuackBehavior;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-06-24
 * Time : 13:56
 * Description:
 * Modified By:
 * version : v1.0
 */
public class Squeak implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("Squeak.");
    }
}
