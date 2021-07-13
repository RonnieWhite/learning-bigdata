package com.baich.bigdata.design_patterns.factory.normal;

import com.baich.bigdata.design_patterns.factory.normal.impl.NormalMiPhone;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-07-12
 * Time : 11:16
 * Description:
 * Modified By:
 * version : v1.0
 */
public class NormalMiPhoneFactory implements NormalPhoneFactory {
    @Override
    public NormalPhone makePhone() {
        return new NormalMiPhone();
    }
}
