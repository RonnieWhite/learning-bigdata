package practise.review.review_dao;

import practise.review.review_pojo.User;

public interface UserDao {
    public boolean login(String username, String password);

    public void regist(User user);
}
