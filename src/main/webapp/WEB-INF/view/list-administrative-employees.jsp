<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Wizyg
  Date: 25.03.2020
  Time: 21:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table>
    <tr>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Email</th>
        <th>Action</th>
    </tr>


    <c:forEach var="tempEmployee" items="${administrativeEmployees}">


        <c:url var="updateLink" value="/administrativeEmployee/showFormForUpdate">
            <c:param name="administrativeEmployeeId" value="${tempEmployee.id}"/>
        </c:url>


        <c:url var="deleteLink" value="/administrativeEmployee/delete">
            <c:param name="administrativeEmployeeId" value="${tempEmployee.id}"/>
        </c:url>

        <tr>
            <td> ${tempEmployee.firstName} </td>
            <td> ${tempEmployee.lastName} </td>
            <td> ${tempEmployee.email} </td>

            <td>
                <!-- display the update link -->
                <a href="${updateLink}">Update</a>
                |
                <a href="${deleteLink}"
                   onclick="if (!(confirm('Are you sure you want to delete this teacher?'))) return false">Delete</a>
            </td>

        </tr>

    </c:forEach>

</table>

<button onclick=""></button>
</body>
</html>