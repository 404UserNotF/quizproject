<%--
  Created by IntelliJ IDEA.
  User: 86189
  Date: 2022/8/22
  Time: 11:27
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>add question</title>
</head>
<body>
    <%--model.addAttribute("qzs", quizEntities);--%>
    <%@ include file="adminNav.jsp" %>
    <form action="/admin/question/addQuestion" method="post">
        questionType:
        <select name="quizId">
            <c:forEach items="${qzs}" var="qz">
                <option value="${qz.quizId}">${qz.quizName}</option>
            </c:forEach>
        </select>
        Single Or Multi:
        <select name="choiceType">
            <option value="Normal">single</option>
            <option value="multi">multi</option>
        </select>
        Question Content:<input name="questionContent" type="text">

        <button type="submit">addChoice</button>
    </form>
</body>
</html>
