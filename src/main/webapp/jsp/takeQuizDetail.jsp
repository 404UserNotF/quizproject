<%--
  Created by IntelliJ IDEA.
  User: 86189
  Date: 2022/8/21
  Time: 1:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
    <title>take quiz details</title>
</head>
<body>
    <%--model.addAttribute("qrs", quizResultEntities);--%>
    <%--<c:if test="${c.isSelected == 1}">
        <input type="checkbox" name="userChoices" checked="checked"/><c:if test="${c.isCorrect == 1}"><font color="green" size="5">${c.optionContent}</font></c:if><c:if test="${c.isCorrect == 0}"><b>${c.optionContent}</b></c:if><br>
    </c:if>
    <c:if test="${c.isSelected == 0}">
        <input type="checkbox" name="userChoices"/><c:if test="${c.isCorrect == 1}"><font color="green" size="5">${c.optionContent}</font></c:if><c:if test="${c.isCorrect == 0}"><b>${c.optionContent}</b></c:if><br>
    </c:if>--%>
    <c:forEach var="i" begin="0" end="${qrs.size()-1}">
        <p>${i+1}.  ${qrs.get(i).question.questionContent}</p>
        <c:forEach items="${qrs.get(i).question.choiceEntities}" var="c">
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
