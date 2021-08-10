package com.baich.bigdata.javase.review.review_dao;

import com.baich.bigdata.javase.review.review_pojo.User;

public interface UserDao {
    public boolean login(String username, String password);

    public void regist(User user);
}
