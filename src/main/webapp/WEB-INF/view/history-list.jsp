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
    <title>Historia zatrudnienia</title>


</head>
<body>
<h1>Historia zatrudnienia</h1>

<h2>${employee.firstName} ${employee.lastName}</h2>

<table>
    <thead class="thead-light">
    <tr class="table table-responsive table-bordered">
        <th>Nazwa placówki</th>
        <th>Data rozpoczęcia</th>
        <th>Data zakończenia</th>
        <th>Dni na urlopie bezpłatnym</th>
    </tr>
    </thead>


    <c:forEach var="eh" items="${history}">
        <tr class="d-table-row">
            <td>${eh.institutionName}</td>
            <td>${eh.startDate}</td>
            <td>${eh.endDate}</td>
            <td>${eh.numberOfDaysOnUnpaidLeave}</td>
        </tr>
    </c:forEach>

    <a class="btn btn-primary" href="${pageContext.request.contextPath}/history/add/${employeeId}" class="ui-button">Add
        another work experience</a>

</table>


</body>
</html>
