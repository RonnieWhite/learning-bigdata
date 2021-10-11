package com.baich.bigdata.rpc.common;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-09-07
 * Time : 15:08
 * Description:
 * Modified By:
 * version : v1.0
 */
public interface UserService {
    /**
     * query user info
     *
     * @param id
     * @return
     */
    User findUserById(Integer id);

}
