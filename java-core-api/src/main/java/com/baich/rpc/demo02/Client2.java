package com.baich.rpc.demo02;

import com.baich.rpc.common.UserService;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-09-08
 * Time : 14:52
 * Description:
 * Modified By:
 * version : v1.0
 */
public class Client2 {
    public static void main(String[] args) {
        UserService userService = Stub.getStub();
        System.out.println(userService.findUserById(23));

    }

}
