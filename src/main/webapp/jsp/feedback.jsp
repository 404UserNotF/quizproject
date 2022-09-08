<%--
  Created by IntelliJ IDEA.
  User: 86189
  Date: 2022/8/14
  Time: 16:40
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@ include file="navigation.jsp" %>
<form action="/feedback/saveFeedback">
    give us feedback!!!<br>
    <c:forEach items="${allQuiz}" var="q">
        <input type="radio" name="quizId" value="${q.quizId}">${q.quizName}<br>
    </c:forEach>
    <br>
    <br>
    <input type="radio" name="rating" value="1"> 1 star
    <input type="radio" name="rating" value="2"> 2 star
    <input type="radio" name="rating" value="3"> 3 star
    <input type="radio" name="rating" value="4"> 4 star
    <input type="radio" name="rating" value="5"> 5 star
    <br>
    review :<input type="text" name="review"><br>
    <input type="submit" value="submit">
</form>

</body>
</html>
