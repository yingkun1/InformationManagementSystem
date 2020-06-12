package online.luffyk.service.impl;

import online.luffyk.dao.UserDao;
import online.luffyk.dao.impl.UserDaoImpl;
import online.luffyk.domain.User;
import online.luffyk.service.UserService;
import org.apache.log4j.Logger;

public class UserServiceImpl implements UserService {
    private Logger logger = Logger.getLogger(UserServiceImpl.class);
    private UserDao userDao = new UserDaoImpl();

    @Override
    public User checkUserLoginService(String username, String password) {
        logger.debug("用户"+username+"发起了登录请求");
        User user = userDao.checkUserLoginDao(username, password);
        if(user!=null){
            logger.debug("用户"+username+"登录成功");
        }else{
            logger.debug("用户"+username+"登录失败");
        }
        return user;
    }

    @Override
    public Integer changeUserPwdService(Integer uid, String newPassword) {
        logger.debug("uid为"+uid+"的用户准备修改密码");
        Integer index = userDao.changeUserPwdDao(uid, newPassword);
        if(index>0){
            logger.debug("uid为"+uid+"的用户修改密码成功");
        }else{
            logger.debug("uid为"+uid+"的用户修改密码失败");
        }
        return index;
    }
}
