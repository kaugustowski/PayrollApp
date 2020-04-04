<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
        <th>baseSalary</th>
        <th>calculateSalary</th>
    </tr>


    <c:forEach var="employee" items="${employees}">


        <%--        <c:url var="updateLink" value="/teacher/showFormForUpdate">--%>
        <%--            <c:param name="employeeId" value="${employee.id}"/>--%>
        <%--        </c:url>--%>


        <%--        <c:url var="deleteLink" value="/teacher/delete">--%>
        <%--            <c:param name="teacherId" value="${tempTeacher.id}"/>--%>
        <%--        </c:url>--%>

        <tr>
            <td> ${employee.firstName} </td>
            <td> ${employee.lastName} </td>
            <td> ${employee.email} </td>
            <td>${employee.baseSalary}</td>
            <td>${employee.salary}</td>


                <%--            <td>--%>
                <%--                <a href="${updateLink}">Update</a>--%>
                <%--                |--%>
                <%--                <a href="${deleteLink}"--%>
                <%--                   onclick="if (!(confirm('Are you sure you want to delete this teacher?'))) return false">Delete</a>--%>
                <%--            </td>--%>

        </tr>

    </c:forEach>

    <%--    <c:url var="calculateLink" value="/teacher/calculate">--%>
    <%--                    <c:param name="teacherId" value="${tempTeacher.id}"/>--%>
    <%--    </c:url>--%>


    <%--    <button onclick="${calculateLink}"> Calculate </button>--%>
</table>


</body>
</html>
