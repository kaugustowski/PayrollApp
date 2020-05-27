<%--
  Created by IntelliJ IDEA.
  User: Wizyg
  Date: 25.03.2020
  Time: 21:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>

<head>
    <title>Save Sick leave</title>
</head>

<body>

<div id="wrapper">
    <div id="header">
        <h2>Sick leave form</h2>
    </div>
</div>

<div id="container">
    <h3>Save Sick Leave</h3>

    <h2>${teacher.firstName } ${teacher.lastName }</h2>

    <form:form action="${pageContext.request.contextPath}/teacher/saveSickLeave/${employeeId}"
               modelAttribute="sickLeave" method="POST">

        <%--        <form:hidden path="sickLeaveId"/>--%>

        <table>
            <tbody>
            <tr>
                <td><label>Sick leave start date:</label></td>
                <td><form:input type="date" path="startDate"/></td>
            </tr>

            <tr>
                <td><label>Sick leave end date:</label></td>
                <td><form:input type="date" path="endDate"/></td>
            </tr>

            <tr>
                <td><label></label></td>
                <td><input type="submit" value="Save" class="save"/></td>
            </tr>
            </tbody>
        </table>


    </form:form>

    <div style="clear: both"></div>

    <p>
        <a href="${pageContext.request.contextPath}/teacher/list">Back to List</a>
    </p>

</div>

</body>

</html>
