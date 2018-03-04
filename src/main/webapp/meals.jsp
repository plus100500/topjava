<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%--<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>--%>
<html>
<head>
    <style type="text/css">
        #myTable {
            border-collapse: collapse; /* Collapse borders */
            /*width: 100%; !* Full-width *!*/
            border: 1px solid #ddd; /* Add a grey border */
            font-size: 14px; /* Increase font-size */
        }
        #myTable th, #myTable td {
            text-align: left; /* Left-align text */
            padding: 3px; /* Add padding */
        }
        #myTable tr {
            /* Add a bottom border to all table rows */
            border-bottom: 1px solid #ddd;
        }
        .green{background-color: green}
        .red{background-color: red}
    </style>

    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<h2>Meals</h2>
<hr>
<table id="myTable">
    <tr>
        <th>ID</th>
        <th>Дата</th>
        <th>Описание</th>
        <th>Калорийность</th>
        <th colspan="2">Действие</th>
    </tr>

    <c:forEach items="${mealsWithExceed}" var="mealWithExceed" varStatus="loop">

        <tr class="${mealWithExceed.exceed ? 'red':'green'}">
            <td>
                <c:out value="${mealWithExceed.id}" />
            </td>
            <td>
                <c:out value="${mealWithExceed.dateTimeCustom}" />
            </td>
            <td>
                <c:out value="${mealWithExceed.description}" />
            </td>
            <td>
                <c:out value="${mealWithExceed.calories}" />
            </td>
            <td>
                <a href="meals?action=edit&id=<c:url value='${mealWithExceed.id}'/>">Редактировать</a>
            </td>
            <td>
                <a href="meals?action=remove&id=<c:url value='${mealWithExceed.id}'/>">Удалить</a>

            </td>
        </tr>
    </c:forEach>
</table>
<hr>

<c:if test="${empty meal.id}" >
    <h1>Добавить</h1>
</c:if>
<c:if test="${!empty meal.id}" >
    <h1>Обновить</h1>
</c:if>

<form method="POST" action="meals" name="add">

    ID : <input type="text" name="id" readonly="true" value="<c:out value="${meal.id}"/>" />
    <br />
    DateTime : <input type="datetime-local" name="dateTime" value="<c:out value="${meal.dateTime}"/>" />
    <br />
    Description : <input type="text" name="description" value="<c:out value="${meal.description}"/>" />
    <br />
    Calories : <input type="text" name="calories" value="<c:out value="${meal.calories}"/>" />
    <br />
        <c:if test="${empty meal.id}">
            Submit : <input type="submit" name="submit" value="Добавить"/>
        </c:if>
        <c:if test="${!empty meal.id}">
            Submit : <input type="submit" name="submit" value="Обновить"/>
        </c:if>

</form>
</body>
</html>
