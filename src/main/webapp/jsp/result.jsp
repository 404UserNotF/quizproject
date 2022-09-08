<%--
  Created by IntelliJ IDEA.
  User: 86189
  Date: 2022/8/14
  Time: 13:09
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>result</title>
</head>
<body>
<%@ include file="navigation.jsp" %>
    <c:if test="${qa.correctCount>=6}">
        <h1>${username} Pass The ${quizname} Test, you got ${qa.correctCount} questions right</h1>
    </c:if>
    <c:if test="${qa.correctCount<6}">
        <h1>${username} Failed ${quizname} test, you got ${qa.correctCount} questions right</h1>
    </c:if>
    ${startTime} -- ${endTime}
    <form action="/">
        <input type="submit" value="back">
    </form>
    <br>
    <c:forEach var = "i" begin = "0" end = "9">
        <b>${i+1}. ${qa.questionAndChoices.get(i).question.questionContent}</b><br>
        <c:forEach items="${qa.questionAndChoices.get(i).choices}" var="c">
            <c:if test="${c.isSelected == 1}">
                <input type="checkbox" name="userChoices" checked="checked"/><c:if test="${c.isCorrect == 1}"><font color="green" size="5">${c.optionContent}</font></c:if><c:if test="${c.isCorrect == 0}"><b>${c.optionContent}</b></c:if><br>
            </c:if>
            <c:if test="${c.isSelected == 0}">
                <input type="checkbox" name="userChoices"/><c:if test="${c.isCorrect == 1}"><font color="green" size="5">${c.optionContent}</font></c:if><c:if test="${c.isCorrect == 0}"><b>${c.optionContent}</b></c:if><br>
            </c:if>
        </c:forEach>
    </c:forEach>
</body>
</html>
