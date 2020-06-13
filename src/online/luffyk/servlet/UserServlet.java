package online.luffyk.servlet;

import online.luffyk.domain.User;
import online.luffyk.service.UserService;
import online.luffyk.service.impl.UserServiceImpl;
import online.luffyk.util.Utils;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class UserServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();
    private Logger logger = Logger.getLogger(UserServlet.class);
    private Utils utils = new Utils();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        String oper = req.getParameter("oper");
        logger.info("oper===="+oper);
        if(oper.equals("login")){
            checkUserLogin(req,resp);
        }else if(oper.equals("logout")){
            logger.info("用户准备退出");
            checkUserLogOut(req,resp);
        }else if(oper.equals("changePwd")){
            logger.info("用户准备修改密码");
            changeUserPwd(req,resp);
        }else if(oper.equals("show")){
            logger.info("用户准备查询所有的用户信息");
            showAllUser(req,resp);
        }else if(oper.equals("register")){
            logger.info("用户准备注册");
            userRegister(req,resp);
        }
        else{
            logger.info("没有找到对应的操作符");
        }
    }

    private void userRegister(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String sex = req.getParameter("sex");
        String age = req.getParameter("age");
        String birthday = req.getParameter("birthday");
        logger.debug("birthday="+birthday);
        if(!birthday.equals("")){
            birthday = utils.dateConversion(birthday);
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setSex(sex);
        if(!age.equals("")){
            user.setAge(Integer.parseInt(age));
        }
        if(!birthday.equals("")){
            user.setBirthday(birthday);
        }
        logger.info("user="+user);
        Integer index = userService.userInfoInsertService(user);
        if(index>0){
            HttpSession session = req.getSession();
            session.setAttribute("register",true);
            resp.sendRedirect("/manager/login.jsp");
        }
    }

    private void showAllUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = userService.showAllUserService();
        if(users!=null&&users.size()>0){
//            HttpSession session = req.getSession();
//            session.setAttribute("users",users);
//            resp.sendRedirect("/manager/user/showUserInfo.jsp");
            logger.debug("size=="+users.size());
            req.setAttribute("users",users);
            req.getRequestDispatcher("/user/showUserInfo.jsp").forward(req,resp);
        }
    }

    private void changeUserPwd(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String newPassword = req.getParameter("newPassword");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        Integer uid = user.getUid();
        Integer index = userService.changeUserPwdService(uid, newPassword);

        if(index>0){
            //密码修改成功，使session失效,暂时先不失效
            session.setAttribute("changed",true);
            //重定向到登陆页面
            resp.sendRedirect("/manager/login.jsp");
        }
    }

    private void checkUserLogOut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //获取session
        HttpSession session = req.getSession();
        logger.debug("用户"+((User) session.getAttribute("user")).getUsername()+"退出了");
        //使session失效
        session.invalidate();
        //重定向到login页面
        resp.sendRedirect("/manager/login.jsp");
    }

    private void checkUserLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User user = userService.checkUserLoginService(username, password);
        logger.debug(user);
        if(user!=null){
            HttpSession session = req.getSession();
            session.setAttribute("user",user);
            resp.sendRedirect("http://127.0.0.1:8080/manager/main/main.jsp");
        }else{
            req.setAttribute("flag",false);
            req.getRequestDispatcher("/login.jsp").forward(req,resp);
        }
    }


}
