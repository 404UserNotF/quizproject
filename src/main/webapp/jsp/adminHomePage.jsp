<%--
  Created by IntelliJ IDEA.
  User: 86189
  Date: 2022/8/20
  Time: 11:37
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
    <title>Title</title>
</head>

<body>
    <%@ include file="adminNav.jsp" %>
    <table border="1" width="100%" cellspacing="2">
        <tr>
            <th>No.</th>
            <th><a href="/takeQuizRecord?toPage=1&limit=${limit}&sortedBy=1&quizId=${picked}">Taken_Date</a></th>
            <th><a href="/takeQuizRecord?toPage=1&limit=${limit}&sortedBy=3&quizId=${picked}">Category</a></th>
            <th><a href="/takeQuizRecord?toPage=1&limit=${limit}&sortedBy=2&quizId=${picked}">Username</a></th>
            <th>Score</th>
        </tr>
        <%--<tr>
            <td>Alfreds Futterkiste</td>
            <td>Maria Anders</td>
            <td>Germany</td>
        </tr>--%>
        <jsp:useBean id="timestamp" class="java.util.Date"/>
        <c:forEach var="i" begin="0" end="${records.size()-1}">
            <tr>
                <jsp:setProperty name="timestamp" property="time" value="${records.get(i).startTime}"/>
                <%--transfer time from time stamp to MM/dd/yyyy format--%>
                <td><a href="/takeQuizRecord/toRecordDetail?takeQuizId=${records.get(i).takeQuizId}">${from+i+1}</a></td>
                <td><fmt:formatDate value="${timestamp}" pattern="MM/dd/yyyy HH:mm:ss"/></td>
                <td>${records.get(i).quiz.quizName}</td>
                <td>${records.get(i).user.userName}</td>
                <td>${records.get(i).score}</td>
            </tr>
        </c:forEach>
    </table>
    <%--
        model.addAttribute("records", takeQuizEntities);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("curPage", 1);
        model.addAttribute("from", 0);
        model.addAttribute("limit", Constants.DEFAULT_LIMIT_OF_PAGE);
        model.addAttribute("sortedBy", Constants.SORTED_BY_STARTTIME);
        model.addAttribute("qz", quizEntities);
        model.addAttribute("picked", quizId);
    --%>
    <form action="/takeQuizRecord" method="get">
        <c:choose>
            <c:when test="${totalCount%limit!=0}">
                <c:set var = "maxPage" scope = "session" value = "${totalCount/limit+1}"/>
            </c:when>
            <c:otherwise>
                <c:set var = "maxPage" scope = "session" value = "${totalCount/limit}"/>
            </c:otherwise>
        </c:choose>
        <%--count 2 page forward--%>
        <c:choose>
            <c:when test="${(curPage-2)<=0}">
                <c:forEach var="i" begin="${1}" end="${curPage-1}">
                    <a href="/takeQuizRecord?toPage=${i}&limit=${limit}&sortedBy=${sortedBy}&quizId=${picked}">${i}</a>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <c:forEach var="i" begin="${curPage-2}" end="${curPage-1}">
                    <a href="/takeQuizRecord?toPage=${i}&limit=${limit}&sortedBy=${sortedBy}&quizId=${picked}">${i}</a>
                </c:forEach>
            </c:otherwise>
        </c:choose>
        <%--count 2 page backward--%>
        <c:forEach var="i" begin="${curPage}" end="${curPage+2}">
            <c:if test="${i<=maxPage}">
                <a href="/takeQuizRecord?toPage=${i}&limit=${limit}&sortedBy=${sortedBy}&quizId=${picked}">${i}</a>
            </c:if>
        </c:forEach>
        <p>current page: ${curPage}  total records:${totalCount}</p>
        <br>
        records per page:(current records:${limit})
            <a href="/takeQuizRecord?toPage=1&limit=3&sortedBy=${sortedBy}&quizId=${picked}">3</a>
            <a href="/takeQuizRecord?toPage=1&limit=5&sortedBy=${sortedBy}&quizId=${picked}">5</a>
            <a href="/takeQuizRecord?toPage=1&limit=10&sortedBy=${sortedBy}&quizId=${picked}">10</a>
            <a href="/takeQuizRecord?toPage=1&limit=20&sortedBy=${sortedBy}&quizId=${picked}">20</a>
        <br>
        <%--sort by:
        <select name="sortedBy">
            <option value="1" selected>TakenDate</option>
            <option value="2">Username</option>
            <option value="3">Category</option>
        </select>
        <br>--%>
        <br>
        Only show records of this Category:
        <a href="/takeQuizRecord?toPage=1&limit=${limit}&sortedBy=${sortedBy}&quizId=-1">Display All</a>
        <c:forEach items="${qz}" var="q">
            <a href="/takeQuizRecord?toPage=1&limit=${limit}&sortedBy=${sortedBy}&quizId=${q.quizId}">${q.quizName}</a>
        </c:forEach>
    </form>
</body>
</html>
