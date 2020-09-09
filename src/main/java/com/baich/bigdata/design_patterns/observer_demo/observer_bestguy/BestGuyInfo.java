package com.baich.bigdata.design_patterns.observer_demo.observer_bestguy;

import java.util.Observable;

/**
 * Created By IDEA.
 * Author : bai.chenghui
 * Date : Created in 2020-08-10
 * Time : 10:53
 * Description:
 * Modified By:
 * version : v1.0
 */
public class BestGuyInfo extends Observable {
    private String name;
    private float goal;
    private short age;

    public BestGuyInfo() {
    }

    public void setBestGuyInfo(String name, float goal, short age) {
        this.name = name;
        this.goal = goal;
        this.age = age;
        infoChanged();
    }

    public void infoChanged() {
        setChanged();
        notifyObservers();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getGoal() {
        return goal;
    }

    public void setGoal(float goal) {
        this.goal = goal;
    }

    public short getAge() {
        return age;
    }

    public void setAge(short age) {
        this.age = age;
    }


}
