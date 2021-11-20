package com.baich.rpc.common;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-09-07
 * Time : 17:35
 * Description:
 * Modified By:
 * version : v1.0
 */
public class UserServiceImpl implements UserService {
    @Override
    public User findUserById(Integer id) {
        return new User(id, "Sandy");
    }
}
