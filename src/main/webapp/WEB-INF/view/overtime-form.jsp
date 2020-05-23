<%--
  Created by IntelliJ IDEA.
  User: Wizyg
  Date: 25.03.2020
  Time: 21:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<head>
    <title>Overtime</title>
</head>

<body>

<div id="wrapper">
    <div id="header">
        <h2>Teacher form</h2>
    </div>
</div>

<div id="container">
    <h3>add overtime hours</h3>

    <h2>${teacher.firstName } ${teacher.lastName }</h2>

    <form:form action="${pageContext.request.contextPath}/teacher/saveOvertime/${teacher.id}" modelAttribute="overtime"
               method="POST">

        <form:hidden path="id"/>

        <table>
            <tbody>

            <tr>
                <td><label>YearMonth:</label></td>
                <td><form:input type="date" path="yearMonth"/></td>
            </tr>

            <tr>
                <td><label>number of hours:</label></td>
                <td><form:input path="numberOfOverTimeHoursInCurrentMonth"/></td>
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
