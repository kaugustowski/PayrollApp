<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Wizyg
  Date: 16.03.2020
  Time: 18:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Payroll app homepage</title>
</head>
<body>
Welcome to my payroll app

<p>
    <a href="${pageContext.request.contextPath}/teacher/addTeacher">add teacher</a>
</p>

<%--<p>--%>
<%--    <a href="${pageContext.request.contextPath}/administrativeEmployee/addAdministrativeEmployee">add administrative--%>
<%--        employee</a>--%>
<%--</p>--%>

<p>
    <a href="${pageContext.request.contextPath}/teacher/list">teachers list</a>
</p>

<p>
    <a href="${pageContext.request.contextPath}/teacher/addOvertime/1">add overtime for teacher with id =1</a>
</p>

<%--<p>--%>
<%--    <a href="${pageContext.request.contextPath}/administrativeEmployee/list">administrative employees list</a>--%>
<%--</p>--%>

<%--<p>--%>
<%--    <a href="${pageContext.request.contextPath}/employee/list">employees list</a>--%>
<%--</p>--%>

<p>
    <a href="${pageContext.request.contextPath}/teacher/sickLeaves/1">sick leaves list for teacher with id = 1</a>
</p>


<p>
    <a href="${pageContext.request.contextPath}/testForm">testForm</a>
</p>


<p>
    <form:form action="${pageContext.request.contextPath}/logout" method="POST">
        <input type="submit" name="logout" value="logout">
    </form:form>
</p>
</body>
</html>
