<%--
  Created by IntelliJ IDEA.
  User: Wizyg
  Date: 28.05.2020
  Time: 19:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Salary details</title>
</head>

<body>
<p>${salary.employee.firstName} + ${salary.employee.lastName}</p>
<p>${salary.toString()}</p>

<form:form action="${pageContext.request.contextPath}/salary/saveSalary" modelAttribute="salary" method="POST">

    <form:hidden path="id"/>

    ${salary.toString()}


    <table>
        <tbody>

        <tr>
            <td><label></label></td>
            <td><input type="submit" value="Save" class="save"/></td>
        </tr>


        </tbody>
    </table>


</form:form>

</body>
</html>
