<%--
  Created by IntelliJ IDEA.
  User: 86189
  Date: 2022/8/13
  Time: 11:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>login</title>
    <script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
</head>
<body>
    <div id="div_form">
        <form action="/user/doLogin" method="post">
            <span class="word">username</span> <input class="input_text_field" id="username" type="text" name="username"/>
            <br>
            <b>${usernameMsg}</b>
            <br>
            <span class="word">password</span> <input class="input_text_field" id="password" type="password" name="password"/>
            <br>
            <b>${passwordMsg}</b>
            <br>
            <input id="submit_btn" type="submit" value="login">
        </form>
        <form action="/user/toRegister">
            <input id="rigister_btn" type="submit" value="register">
        </form>
    </div>
    <a href="/user/admin/toAdminLogin">Admin Login</a>
</body>
</html>

