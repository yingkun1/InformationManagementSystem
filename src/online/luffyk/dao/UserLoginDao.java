package online.luffyk.dao;

import online.luffyk.domain.User;

public interface UserLoginDao {
    /**
     * 根据用户名和密码去数据库中查询用户信息
     * @param username 用户名
     * @param password 密码
     * @return 返回数据库中查询到的信息
     */
    User checkUserLoginDao(String username, String password);
}
