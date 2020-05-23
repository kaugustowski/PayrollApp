<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Wizyg
  Date: 18.04.2020
  Time: 12:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sick Leaves</title>

    <style>
        table, th, td {
            border: 1px solid black;
        }
    </style>
</head>
<body>
<h1>Sick leaves</h1>
<h2>${teacher.firstName} ${teacher.lastName}</h2>

<table>

    <tr>
        <th>Start date</th>
        <th>End date</th>
        <th>Consecutive Days</th>
    </tr>


    <c:forEach var="sickLeave" items="${sickLeaves}">
        <tr>
            <td>${sickLeave.startDate}</td>
            <td>${sickLeave.endDate}</td>
            <td>${sickLeave.consecutiveDays}</td>
        </tr>
    </c:forEach>

</table>


</body>
</html>
