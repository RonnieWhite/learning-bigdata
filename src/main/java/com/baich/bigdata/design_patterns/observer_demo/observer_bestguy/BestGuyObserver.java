package com.baich.bigdata.design_patterns.observer_demo.observer_bestguy;

import java.util.Observable;
import java.util.Observer;

/**
 * Created By IDEA.
 * Author : bai.chenghui
 * Date : Created in 2020-08-10
 * Time : 11:03
 * Description:
 * Modified By:
 * version : v1.0
 */
public class BestGuyObserver implements Observer {
    private BestGuyInfo bestGuyInfo;
    private String name;
    private float goal;
    private short age;

    public BestGuyObserver(BestGuyInfo bestGuyInfo) {
        this.bestGuyInfo = bestGuyInfo;
        bestGuyInfo.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof BestGuyInfo) {
            BestGuyInfo bestGuyInfo = (BestGuyInfo) o;
            this.name = bestGuyInfo.getName();
            this.goal = bestGuyInfo.getGoal();
            this.age = bestGuyInfo.getAge();
            display();
        }
    }

    public void display() {
        System.out.println("This beautiful girl's name is " + name + ". " + age + " years old. " + "and " + goal + " goals.");
    }
}
