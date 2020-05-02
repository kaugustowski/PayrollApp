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


    <c:forEach var="tempTeacher" items="${teachers}">


        <c:url var="updateLink" value="/teacher/showFormForUpdate">
            <c:param name="teacherId" value="${tempTeacher.id}"/>
        </c:url>


        <c:url var="deleteLink" value="/teacher/delete">
            <c:param name="teacherId" value="${tempTeacher.id}"/>
        </c:url>

        <c:url var="addSickLeaveLink" value="/teacher/addSickLeave/${tempTeacher.id}">
            <%--            <c:param name="teacherId" value="${tempTeacher.id}"/>--%>
        </c:url>


        <tr>
            <td> ${tempTeacher.firstName} </td>
            <td> ${tempTeacher.lastName} </td>
            <td> ${tempTeacher.email} </td>
                ${tempTeacher.setBaseSalary()}
            <td>${tempTeacher.baseSalary}</td>
            <td>${tempTeacher.calculateSalary()}</td>


            <td>
                <a href="${updateLink}">Update</a>
                |
                <a href="${addSickLeaveLink}">Add sick leave</a>
                |
                <a href="${deleteLink}"
                   onclick="if (!(confirm('Are you sure you want to delete this teacher?'))) return false">Delete</a>
            </td>

        </tr>

    </c:forEach>

    <c:url var="calculateLink" value="/teacher/calculate">
        <%--            <c:param name="teacherId" value="${tempTeacher.id}"/>--%>
    </c:url>


    <button onclick="${calculateLink}"> Calculate</button>
</table>


</body>
</html>
