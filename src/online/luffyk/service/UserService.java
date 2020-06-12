package online.luffyk.service;

import online.luffyk.domain.User;

public interface UserService {
    /**
     * 校验用户信息
     * @param username 用户名
     * @param password 密码
     * @return 返回用户信息
     */
    User checkUserLoginService(String username, String password);

    /**
     * 修改用户的密码
     * @param uid   用户的标识符
     * @param newPassword 用户的新密码
     * @return 返回一个索引值用于标识是否修改成功
     */
    Integer changeUserPwdService(Integer uid,String newPassword);

}