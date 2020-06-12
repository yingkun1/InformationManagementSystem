<%--
  Created by IntelliJ IDEA.
  User: luffyk
  Date: 2020/6/12
  Time: 16:55
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
    <base href="<%=basePath%>" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>无标题文档</title>
    <link href="css/style.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="js/jquery.js"></script>
    <script type="text/javascript">
        $(function () {
            $("#fm").submit(function(){
                let newPassword =  $("#newPassword").val();
                let confirmPassword = $("#confirmPassword").val();
                console.log(newPassword);
                console.log(confirmPassword);
                if(newPassword==""){
                    alert("新密码不能为空");
                    return false
                }else if(confirmPassword==""){
                    alert("确认密码不能为空");
                    return false
                }else if(newPassword!=confirmPassword){
                    alert("新密码和确认密码不一致");
                    return false;
                }else{
                    return true
                }
            })
        })

    </script>
</head>

<body>

<div class="place">
    <span>位置：</span>
    <ul class="placeul">
        <li><a href="#">首页</a></li>
        <li><a href="#">个人信息</a></li>
        <li><a href="#">修改密码</a></li>
    </ul>
</div>

<div class="formbody">

    <div class="formtitle"><span>基本信息</span></div>

    <form action="user" method="post" id="fm" target="_top" >
        <input type="hidden" name="oper" value="changePwd" />
        <ul class="forminfo">
            <li><label>新密码</label><input name="newPassword" id="newPassword" type="text" class="dfinput" /><i>密码不能超过10个字符</i></li>
            <li><label>确认密码</label><input type="text" id="confirmPassword" class="dfinput" /><i>密码不能超过10个字符</i></li>
            <li><label>&nbsp;</label><input type="submit" value="确认修改"/></li>
        </ul>
    </form>



</div>


</body>

</html>
