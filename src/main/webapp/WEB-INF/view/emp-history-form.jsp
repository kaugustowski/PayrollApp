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
    <title>Save Employment History</title>
</head>

<body>

<div id="wrapper">
    <div id="header">
        <h2>Employment history form</h2>
    </div>
</div>

<div id="container">
    <h3>Save Employment history</h3>

    <h2>${employee.firstName } ${employee.lastName }</h2>

    <form:form action="${pageContext.request.contextPath}/history/save/${employeeId}"
               modelAttribute="empHistory" method="POST">

        <%--        <form:hidden path="sickLeaveId"/>--%>

        <table>
            <tbody>
            <tr>
                <td><label>Institution name:</label></td>
                <td><form:input path="institutionName"/></td>
            </tr>

            <tr>
                <td><label>Start date:</label></td>
                <td><form:input type="date" path="startDate"/></td>
            </tr>

            <tr>
                <td><label>End date:</label></td>
                <td><form:input type="date" path="endDate"/></td>
            </tr>

            <tr>
                <td><label>Number of unpaid leave days:</label></td>
                <td><form:input path="numberOfDaysOnUnpaidLeave"/></td>
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
