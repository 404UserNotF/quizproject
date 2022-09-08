<%--
  Created by IntelliJ IDEA.
  User: 86189
  Date: 2022/8/20
  Time: 10:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
    <script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
</head>
<body>
<div id="div_form">
    <form action="/user/admin/doAdminLogin" method="post">
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
</div>
</body>
</html>