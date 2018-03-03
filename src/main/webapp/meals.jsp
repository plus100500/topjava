<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <th>Дата</th>
        <th>Описание</th>
        <th>Калорийность</th>
    </tr>
    <c:forEach items="${mealsWithExceed}" var="mealWithExceed"
               varStatus="loop">
        <tr class="${mealWithExceed.isExceed() ? 'green':'red'}">
            <td>
                <c:out value="${mealWithExceed.getDateTimeCustom()}" />
            </td>
            <td>
                <c:out value="${mealWithExceed.getDescription()}" />
            </td>
            <td>
                <c:out value="${mealWithExceed.getCalories()}" />
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
