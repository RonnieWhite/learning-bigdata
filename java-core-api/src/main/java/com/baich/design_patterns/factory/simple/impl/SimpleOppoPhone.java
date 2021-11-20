package com.baich.design_patterns.factory.simple.impl;

import com.baich.design_patterns.factory.simple.SimplePhone;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-07-12
 * Time : 10:20
 * Description:
 * Modified By:
 * version : v1.0
 */
public class SimpleOppoPhone implements SimplePhone {

    @Override
    public void make() {
        System.out.println("Make a Oppo...");
    }
}
