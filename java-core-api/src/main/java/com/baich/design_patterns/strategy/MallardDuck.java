package com.baich.design_patterns.strategy;

import com.baich.design_patterns.strategy.fly.impl.FlyWithWings;
import com.baich.design_patterns.strategy.quack.impl.Quack;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-06-24
 * Time : 13:47
 * Description:
 * Modified By:
 * version : v1.0
 */
public class MallardDuck extends Duck {
    public MallardDuck() {
        quackBehavior = new Quack();
        flyBehavior = new FlyWithWings();
    }

    @Override
    public void display() {
        System.out.println("I'm a real Mallard duck.");
    }

    public static void main(String[] args) {
        Duck duck = new MallardDuck();
//        duck.display();
        duck.performFlay();
    }
}
