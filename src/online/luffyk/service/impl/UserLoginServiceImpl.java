package online.luffyk.service.impl;

import online.luffyk.dao.UserLoginDao;
import online.luffyk.dao.impl.UserLoginDaoImpl;
import online.luffyk.domain.User;
import online.luffyk.service.UserLoginService;

public class UserLoginServiceImpl implements UserLoginService {
    private UserLoginDao userLoginDao = new UserLoginDaoImpl();

    @Override
    public User checkUserLoginService(String username, String password) {
        return userLoginDao.checkUserLoginDao(username, password);
    }
}
