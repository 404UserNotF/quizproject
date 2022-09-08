
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>question</title>
        <script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
    </head>
    <body>
        <%@ include file="navigation.jsp" %>
        <form action="/question/answer?index=${index}" method="post" >
            <p id="timer"></p>
            <p>${index+1}. ${qa.questionAndChoices.get(index).question.questionContent}<p><br/>
            <%--<c:forEach items="${qa.questionAndChoices.get(index).choices}" var="c">
                <c:if test="${c.isSelected == 1}">
                    <input type="checkbox" name="userChoices" value="${c.choiceId}" checked="checked"/>${c.optionContent}
                </c:if>
                <c:if test="${c.isSelected == 0}">
                    <input type="checkbox" name="userChoices" value="${c.choiceId}"/>${c.optionContent}
                </c:if>
            </c:forEach>--%>
            <c:forEach items="${qa.questionAndChoices.get(index).choices}" var="c">
                <c:if test="${c.isSelected == 1}">
                    <c:if test="${qa.questionAndChoices.get(index).question.questionStatus == 'multi'}">
                        <input type="checkbox" name="userChoices" value="${c.choiceId}" checked="checked"/>${c.optionContent}
                    </c:if>
                    <c:if test="${qa.questionAndChoices.get(index).question.questionStatus != 'multi'}">
                        <input type="radio" name="userChoices" value="${c.choiceId}" checked="checked"/>${c.optionContent}
                    </c:if>
                </c:if>
                <c:if test="${c.isSelected == 0}">
                    <c:if test="${qa.questionAndChoices.get(index).question.questionStatus == 'multi'}">
                        <input type="checkbox" name="userChoices" value="${c.choiceId}"/>${c.optionContent}
                    </c:if>
                    <c:if test="${qa.questionAndChoices.get(index).question.questionStatus != 'multi'}">
                        <input type="radio" name="userChoices" value="${c.choiceId}" />${c.optionContent}
                    </c:if>
                </c:if>
            </c:forEach>
            <br>
            jump to question:<br>
            <c:forEach var = "i" begin = "0" end = "${qa.questionAndChoices.size()-1}">
            <button type="submit" name = "toIndex" value = "${i}">${i+1}</button>
            </c:forEach>
            <%--<input type="checkbox" name="toIndex" value="${c.choiceId}"/>${c.optionContent}--%>
            <br>
            <c:if test = "${index != 0}">
            <button type="submit" class="btn btn-primary" id="previous" name = "action" value = "previous">Previous</button>
            </c:if>
            <c:if test = "${index != qa.questionAndChoices.size()-1}">
            <button type="submit" class="btn btn-primary" id="next" name = "action" value = "next">Next</button>
            </c:if>
            <button type="submit" class="btn btn-primary" id="subQa" name = "action" value = "finish">Submit Questionnaire</button>
            <br>
        </form>
    </body>
</html>

<script type="text/javascript">
    function formatTime(mss){
        // 1 sec = 1000 ms
        var seconds = parseInt(mss/1000%60);
        var minutes = parseInt(mss/1000/60);
        return minutes+":"+seconds;
    }
    (function poll() {
        $.ajax({
            url: "/time/verify",
            type: "GET",
            data:{"curTime":(new Date()).valueOf()},
            success: function(response) {
                if(response.message == 'timeout'){
                    window.alert("Time out, generating results");
                    window.location.href = response.url;
                }else{
                    $("#timer").html(formatTime(response.timeLeft));
                    setTimeout(function() {poll()}, 1000)
                }
            },
            dataType: "json",
            /*complete: setTimeout(function() {poll()}, 1000),
            timeout: 2000*/
        })
    })();
</script>
