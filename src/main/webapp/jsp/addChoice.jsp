<%--
  Created by IntelliJ IDEA.
  User: 86189
  Date: 2022/8/22
  Time: 12:27
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>addChoice</title>
    <script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
</head>
<body>
    <%--model.addAttribute("questionContent", newQuestion.getQuestionContent());--%>
    <%@ include file="adminNav.jsp" %>
    <p>${questionContent}</p><br>
    <div id="addedChoiceDiv">

    </div>
    <div>
        ChoiceContent: <input id="choiceContent" type="text" name="optionContent"><br>
        isCorrect:
        <select id="isCorrect" name="isCorrect">
            <option value="1">correct</option>
            <option value="0">false</option>
        </select>
        <br>
        <button onclick="submitNewChoice()">addChoice</button>
    </div>
    <form action="/admin/question/saveQuestion">
        <button type="submit">save</button>
    </form>
</body>
</html>
<script>
    function submitNewChoice(){
        var optionContent = document.getElementById("choiceContent").value;
        var isCorrectSelector = document.getElementById("isCorrect");
        var selectIdx = isCorrectSelector.selectedIndex;
        var isCorrect = isCorrectSelector.options[selectIdx].value;
        $.ajax({
            url: "/admin/question/addChoice",
            type: "POST",
            data:{
                "optionContent":optionContent,
                "isCorrect":isCorrect
            },
            dataType: "json",
            success: function(data) {
                var addedContent = data.optionContent;
                var addedCorrect = data.isCorrect;
                var addedDisplay = document.getElementById("addedChoiceDiv");
                addedDisplay.innerHTML += addedContent + " " + addedCorrect + "<br>";
                document.getElementById("choiceContent").value = "";
            }
        })
    }
</script>
