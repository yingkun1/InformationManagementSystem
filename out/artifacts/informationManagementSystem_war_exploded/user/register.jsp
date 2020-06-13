<%--
  Created by IntelliJ IDEA.
  User: luffyk
  Date: 2020/6/13
  Time: 14:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>"/>
    <meta charset="UTF-8">
    <title>Basic ValidateBox - jQuery EasyUI Demo</title>
    <link rel="stylesheet" type="text/css" href="css/easyui.css">
    <link rel="stylesheet" type="text/css" href="css/icon.css">
    <link rel="stylesheet" type="text/css" href="css/demo.css">

    <script type="text/javascript" src="demo/jquery.min.js"></script>
    <script type="text/javascript" src="js/jquery.easyui.min.js"></script>
</head>
<body>
<div style="margin:20px 0;"></div>
<div class="easyui-panel" title="用户注册" style="width:800px;padding:100px 100px 100px 100px;height: 500px">
    <form action="user" method="post">
        <input type="hidden" name="oper" value="register">
        <table cellpadding="5">
            <tr>
                <td>用户名:</td>
                <td><input name="username" value="" class="easyui-validatebox textbox" data-options="required:true" missingMessage="用户名不能为空"></td>
            </tr>
            <tr>
                <td>密码:</td>
                <td><input name="password" value="" class="easyui-validatebox textbox" data-options="required:true" missingMessage="密码不能为空"></td>
            </tr>
            <tr>
                <td>性别:</td>
                <td>
                    男：<input type="radio" name="sex" value="1" checked="checked"/>
                    女：<input type="radio" name="sex" value="0"/>
                </td>
            </tr>
            <tr>
                <td>年龄:</td>
                <td><input name="age" value="" class="easyui-validatebox textbox" /></td>
            </tr>
            <tr>
                <td>生日:</td>
                <td><input name="birthday" value="" class="easyui-datebox textbox" /></td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="注册">
                </td>
            </tr>
        </table>
    </form>


</div>
<style scoped="scoped">
    .textbox{
        height:20px;
        margin:0;
        padding:0 2px;
        box-sizing:content-box;
    }
</style>

</body>
</html>
