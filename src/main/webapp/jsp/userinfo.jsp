<%--
  Created by IntelliJ IDEA.
  User: 86189
  Date: 2022/8/21
  Time: 12:11
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
    <title>userinfo</title>
    <script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
</head>
<body>
    <%@ include file="adminNav.jsp" %>
    <%--model.addAttribute("users", users);
    model.addAttribute("curPage", toPage);
    model.addAttribute("limit", limit);
    model.addAttribute("totalCount",total);--%>
    <table border="1" width="100%" cellspacing="2">
        <tr>
            <th>No.</th>
            <th>ID</th>
            <th>username</th>
            <th>email</th>
            <th>role</th>
            <th>status</th>
            <th>changeTo</th>
        </tr>
        <c:forEach var="i" begin="0" end="${users.size()-1}">
            <tr>
                <td>${from+i+1}</td>
                <td>${users.get(i).userId}</td>
                <td>${users.get(i).userName}</td>
                <td>${users.get(i).userEmail}</td>
                <td>${users.get(i).userRole}</td>
                <td id="${users.get(i).userId}setRole">${users.get(i).userStatus}</td>
                <td>
                    <button class="btn" id="${users.get(i).userId}" type="button">
                        <c:if test="${users.get(i).userStatus=='active'}">suspend</c:if>
                        <c:if test="${users.get(i).userStatus!='active'}">active</c:if>
                    </button>
                </td>
            </tr>
        </c:forEach>
    </table>
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
                <a href="/admin/getAllUser?toPage=${i}&limit=${limit}">${i}</a>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <c:forEach var="i" begin="${curPage-2}" end="${curPage-1}">
                <a href="/admin/getAllUser?toPage=${i}&limit=${limit}">${i}</a>
            </c:forEach>
        </c:otherwise>
    </c:choose>
    <%--count 2 page backward--%>
    <c:forEach var="i" begin="${curPage}" end="${curPage+2}">
        <c:if test="${i<=maxPage}">
            <a href="/admin/getAllUser?toPage=${i}&limit=${limit}">${i}</a>
        </c:if>
    </c:forEach>
    <p>current page: ${curPage}  total records:${totalCount}</p>
</body>
</html>
<script type="text/javascript">
    var btn = document.getElementsByTagName("button");

    document.body.onclick = function(event){    //冒泡处理
        var userId = event.target.id;
        var toStatus = event.target.innerHTML;
        toStatus = toStatus.trim();
        $.ajax({
            url: "/admin/changeUserStatusTo",
            type: "POST",
            data:{
                "userId":userId,
                "toStatus":toStatus
            },
            dataType: "json",
            success: function(response) {
                event.target.innerHTML = response.message;
                var displayId = userId+'setRole';
                var roleColumn = document.getElementById(displayId);
                roleColumn.innerHTML = toStatus;
            }
        })
    }
</script>
