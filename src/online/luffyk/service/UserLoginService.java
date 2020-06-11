package online.luffyk.service;

import online.luffyk.domain.User;

public interface UserLoginService {
    /**
     * 校验用户信息
     * @param username 用户名
     * @param password 密码
     * @return 返回用户信息
     */
    User checkUserLoginService(String username, String password);
}
