<%--
  Created by IntelliJ IDEA.
  User: 86189
  Date: 2022/8/13
  Time: 11:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>register</title>
    <script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
</head>
<body>
    <b>${message}</b>
    <form action="/user/doRegister" method="post">
        <span class="word">username</span> <input  id="username" type="text" name="username"/>
        <br>
        <span class="word">password</span> <input  id="password" type="password" name="password"/>
        <br>
        <span class="word">email</span> <input  id="email" type="text" name="email"/>
        <input type="submit" name="submit">
    </form>
</body>
</html>
