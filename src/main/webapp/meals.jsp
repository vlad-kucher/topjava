<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
    <h3><a href="index.html">Home</a></h3>
    <h2>Meal list</h2>
    <hr/>
    <table cellpadding="10" border="1" cellspacing="0">
        <tr>
            <td>Date/Time</td>
            <td>Description</td>
            <td>Calories</td>
        </tr>
        <c:forEach items="${meals}" var="meal">
            <jsp:useBean id="meal" scope="page" type="ru.javawebinar.topjava.model.MealWithExceed"/>
            <tr style="color:${meal.exceed?'red':'green'}">
                <td>${meal.dateTime.toLocalDate()}  ${meal.dateTime.toLocalTime()}</td>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
