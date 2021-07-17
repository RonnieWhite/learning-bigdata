package com.baich.bigdata.design_patterns.strategy.fly.impl;

import com.baich.bigdata.design_patterns.strategy.fly.FlyBehavior;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-06-24
 * Time : 13:53
 * Description:
 * Modified By:
 * version : v1.0
 */
public class FlyNoWay implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("I can't fly.");
    }
}
