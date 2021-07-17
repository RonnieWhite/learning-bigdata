package com.baich.bigdata.design_patterns.strategy;

import com.baich.bigdata.design_patterns.strategy.fly.FlyBehavior;
import com.baich.bigdata.design_patterns.strategy.quack.QuackBehavior;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-06-24
 * Time : 13:43
 * Description:
 * Modified By:
 * version : v1.0
 */
public abstract class Duck {
    FlyBehavior flyBehavior;
    QuackBehavior quackBehavior;

    public Duck() {
    }

    public abstract void display();

    public void performFlay() {
        flyBehavior.fly();
    }

    public void performQuack() {
        quackBehavior.quack();
    }

    public void swim() {
        System.out.println("All ducks float, even decoys!");
    }
}
