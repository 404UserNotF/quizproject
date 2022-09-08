<%--
  Created by IntelliJ IDEA.
  User: 86189
  Date: 2022/8/12
  Time: 19:07
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
</head>
<body>
    <%@ include file="navigation.jsp" %>
    Welcome, select the task you want:
    <form action="/question/startTest">
        <c:forEach items="${quizs}" var="q">
            <input type="radio" name="type" value="${q.quizId}">${q.quizName}, ${q.quizDescription}<br>
        </c:forEach>
        <input type="submit" value="start">
    </form>
</body>
</html>
