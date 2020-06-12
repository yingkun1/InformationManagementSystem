package online.luffyk.dao;

import online.luffyk.domain.User;

public interface UserDao {
    /**
     * 根据用户名和密码去数据库中查询用户信息
     * @param username 用户名
     * @param password 密码
     * @return 返回数据库中查询到的信息
     */
    User checkUserLoginDao(String username, String password);

    /**
     * 修改数据库中用户的密码
     * @param uid 用户的标识符
     * @param newPassword   用户填写的新密码
     * @return  返回一个标识符，用于标识是否更新成功
     */
    Integer changeUserPwdDao(Integer uid,String newPassword);
}
