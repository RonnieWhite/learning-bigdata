package com.white.learning_bigdata.practise_java.day17.dao.impl;

import com.white.learning_bigdata.practise_java.day17.dao.UserDao;
import com.white.learning_bigdata.practise_java.day17.pojo.User;

import java.util.ArrayList;

/**
 * 用户操作具体实现
 */
public class UserDaoImpl implements UserDao {
    // 为了让多个对象共享同一个成员变量，用static
    private static ArrayList<User> array = new ArrayList<User>();

    @Override
    public boolean isLogin(String username, String password) {
        // 遍历集合，获取每一个用户，并判断该用户的用户名和密码是否和传递过来的匹配
        boolean flag = false;
        for (User u : array) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    @Override
    public void regist(User user) {
        array.add(user);
    }

    public void toStr() {
        System.out.println(array);
    }
}
