<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
</head>
<body>
<h2>登录</h2>
<form action="/first/user/login" method="post">
    用户名：<input type="text" name="name">
    密码：<input type="password" name="password">
    <input type="submit"  value="登录">
    <a href="/first/user/register">注册</a>
</form>
</body>
<script>
    if(${status} == 0){
        alert("登录成功");
        location.href="/first/home";
    }else{
        alert("登录失败，用户名或密码错误");
    }
</script>
</html>

