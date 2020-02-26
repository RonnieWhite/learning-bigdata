package main.java.practise.review.review_dao;

import main.java.practise.review.review_pojo.User;

public interface UserDao {
    public boolean login(String username, String password);

    public void regist(User user);
}
