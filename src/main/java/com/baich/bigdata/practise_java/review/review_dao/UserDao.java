package com.baich.bigdata.practise_java.review.review_dao;

import com.baich.bigdata.practise_java.review.review_pojo.User;

public interface UserDao {
    public boolean login(String username, String password);

    public void regist(User user);
}
