package online.luffyk.servlet;

import online.luffyk.domain.User;
import online.luffyk.service.UserLoginService;
import online.luffyk.service.impl.UserLoginServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UserServlet extends HttpServlet {
    private UserLoginService userLoginService = new UserLoginServiceImpl();
    private Logger logger = Logger.getLogger(UserServlet.class);
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
        }else{
            logger.info("没有找到对应的操作符");
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
        User user = userLoginService.checkUserLoginService(username, password);
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
