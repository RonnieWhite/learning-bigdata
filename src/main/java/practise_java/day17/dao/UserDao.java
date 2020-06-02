package practise_java.day17.dao;

import practise_java.day17.pojo.User;

public interface UserDao {
    /**
     * 登录
     * 返回登录是否成功
     *
     * @param username
     * @param password
     * @return
     */
    public abstract boolean isLogin(String username, String password);

    /**
     * 注册
     *
     * @param user
     */
    public abstract void regist(User user);

}
