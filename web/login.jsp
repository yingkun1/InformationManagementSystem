<%--
  Created by IntelliJ IDEA.
  User: luffyk
  Date: 2020/6/10
  Time: 20:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>欢迎登录后台管理系统</title>
    <link href="css/style.css" rel="stylesheet" type="text/css" />
    <script language="JavaScript" src="js/jquery.js"></script>
    <script src="js/cloud.js" type="text/javascript"></script>

    <script language="javascript">
        $(function(){
            $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
            $(window).resize(function(){
                $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
            })
        });
    </script>

</head>

<body style="background-color:#df7611; background-image:url(images/light.png); background-repeat:no-repeat; background-position:center top; overflow:hidden;">



<div id="mainBody">
    <div id="cloud1" class="cloud"></div>
    <div id="cloud2" class="cloud"></div>
</div>


<div class="logintop">
    <span>欢迎登录后台管理界面平台</span>
</div>

<div class="loginbody">

    <span class="systemlogo"></span>
    <%
        Boolean changed = (Boolean) session.getAttribute("changed");
        if(changed!=null&&changed){
    %>
        <script type="text/javascript">
            $(function(){
                alert("修改密码成功")
            })
        </script>
    <%
        }
        session.removeAttribute("changed");
    %>
    <%
    Boolean flag = (Boolean) request.getAttribute("flag");
    System.out.println(flag);
    if(flag!=null&&!flag){
    %>
         <div style="text-align: center;color: white;">
            <span>用户名或密码错误</span>
        </div>
    <%
        }
    %>

   
    <div class="loginbox loginbox1">

        <form action="user" method="post">
            <input type="hidden" name="oper" value="login">
            <ul>
               
                <li><input name="username" placeholder="用户名" type="text"  class="loginuser"/></li>
                <li><input name="password" placeholder="密码" type="password" class="loginpwd"/></li>
                <li class="yzm">
                    <span><input name="" type="text" value="验证码" onclick="JavaScript:this.value=''"/></span><cite>X3D5S</cite>
                </li>
                <li><input name="" type="submit" class="loginbtn" value="登录" /><label><input name="" type="checkbox" value="" checked="checked" />记住密码</label><label><a href="#">忘记密码？</a></label></li>
            </ul>
        </form>


    </div>

</div>


<div class="loginbm">版权所有  2020  <a href="http://www.luffyk.online">luffyk.online</a>  仅供学习交流，勿用于任何商业用途，联系方式qq:925712087</div>


</body>

</html>


