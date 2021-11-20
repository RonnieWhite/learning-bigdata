package com.baich.design_patterns.factory.abstractfactory;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-07-18
 * Time : 17:11
 * Description:
 * Modified By:
 * version : v1.0
 */
public class PearFactory implements IFactory {
    @Override
    public Fruit create() {
        return new Pear();
    }
}
