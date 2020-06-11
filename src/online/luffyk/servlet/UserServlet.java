package online.luffyk.servlet;

import online.luffyk.domain.User;
import online.luffyk.service.UserLoginService;
import online.luffyk.service.impl.UserLoginServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserServlet extends HttpServlet {
    private UserLoginService userLoginService = new UserLoginServiceImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        String oper = req.getParameter("oper");
        System.out.println("oper===="+oper);
        if(oper.equals("login")){
            checkUserLogin(req,resp);
        }
    }

    private void checkUserLogin(HttpServletRequest req, HttpServletResponse resp) {
        String username = req.getParameter("username");
        System.out.println("username===="+username);
        String password = req.getParameter("password");
        System.out.println("password===="+password);
        User user = userLoginService.checkUserLoginService(username, password);
        System.out.println("user===="+user);
    }


}
