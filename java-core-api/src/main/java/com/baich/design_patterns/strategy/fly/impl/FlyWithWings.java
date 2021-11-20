package com.baich.design_patterns.strategy.fly.impl;

import com.baich.design_patterns.strategy.fly.FlyBehavior;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-06-24
 * Time : 13:50
 * Description:
 * Modified By:
 * version : v1.0
 */
public class FlyWithWings implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("I'm flying!!");
    }
}
