package com.baich.javase.review.review_dao.impl;

import com.baich.javase.review.review_dao.UserDao;
import com.baich.javase.review.review_pojo.User;

import java.util.ArrayList;

public class UserDaoImpl implements UserDao {
    private static ArrayList<User> array = new ArrayList<>();

    @Override
    public boolean login(String username, String password) {
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
}
