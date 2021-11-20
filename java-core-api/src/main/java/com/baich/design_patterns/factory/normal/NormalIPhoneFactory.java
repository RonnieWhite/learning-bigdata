package com.baich.design_patterns.factory.normal;

import com.baich.design_patterns.factory.normal.impl.NormalIPhone;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-07-12
 * Time : 11:07
 * Description:
 * Modified By:
 * version : v1.0
 */
public class NormalIPhoneFactory implements NormalPhoneFactory{

    @Override
    public NormalPhone makePhone() {
        return new NormalIPhone();
    }
}
