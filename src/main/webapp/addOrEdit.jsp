<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
<head>
    <title>Add or Edit</title>
</head>
<body>

    <form method="post" action="meals">
        <h3>Add/Edit</h3>
        <input type="hidden" name="id" readonly="readonly"
            value="<c:out value="${meal.id}"/>">
        Date/Time : <input type="datetime-local" name="dateTime"
            value="<c:out value="${meal.dateTime}"/>"> <br/>
        Description : <input type="text" name="description"
            value="<c:out value="${meal.description}"/>"> <br/>
        Calories : <input type="number" name="calories"
            value="<c:out value="${meal.calories}"/>"> <br/>
        <input type="submit" value="Submit">
    </form>

</body>
</html>
