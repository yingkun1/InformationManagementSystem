package online.luffyk.service.impl;

import online.luffyk.dao.UserLoginDao;
import online.luffyk.dao.impl.UserLoginDaoImpl;
import online.luffyk.domain.User;
import online.luffyk.service.UserLoginService;
import org.apache.log4j.Logger;

public class UserLoginServiceImpl implements UserLoginService {
    private Logger logger = Logger.getLogger(UserLoginServiceImpl.class);
    private UserLoginDao userLoginDao = new UserLoginDaoImpl();

    @Override
    public User checkUserLoginService(String username, String password) {
        logger.debug("用户"+username+"发起了登录请求");
        User user = userLoginDao.checkUserLoginDao(username, password);
        if(user!=null){
            logger.debug("用户"+username+"登录成功");
        }else{
            logger.debug("用户"+username+"登录失败");
        }
        return user;
    }
}
