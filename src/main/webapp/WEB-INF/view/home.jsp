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
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>

</head>
<body>

<nav class="navbar sticky-top navbar-dark bg-transparent">
    <ul class="nav nav-tabs">
        <li>
            <a class="nav-link active navbar-brand" href="#">Home</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href=${pageContext.request.contextPath}/employee/list">Employees</a>
        </li>
        <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" data-toggle="dropdown"
            href="#" role="button" aria-haspopup="true" aria-expanded="false">
                Teacher</a>
            <div class="dropdown-menu">
                <a class="dropdown-item" href="${pageContext.request.contextPath}/teacher/addTeacher">Add teacher</a>
                <a class="dropdown-item" href="${pageContext.request.contextPath}/teacher/list">List</a>
            </div>
        </li>
        <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" data-toggle="dropdown"
               href="#" role="button" aria-haspopup="true" aria-expanded="false">
                Salary</a>
            <div class="dropdown-menu">
                <a class="dropdown-item" href="${pageContext.request.contextPath}/salary/essentialList">Essential</a>
                <a class="dropdown-item" href="${pageContext.request.contextPath}/salary/overtimeList">Overtime</a>
                <a class="dropdown-item" href="${pageContext.request.contextPath}/salary/list">Combined</a>

            </div>
        </li>
        <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" data-toggle="dropdown"
               href="#" role="button" aria-haspopup="true" aria-expanded="false">
                Teacher</a>
            <div class="dropdown-menu">
                <a class="dropdown-item" href="${pageContext.request.contextPath}/teacher/addTeacher">Add teacher</a>
                <a class="dropdown-item" href="${pageContext.request.contextPath}/teacher/list">List</a>
            </div>
        </li>
        <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" data-toggle="dropdown"
               href="#" role="button" aria-haspopup="true" aria-expanded="false">
                Teacher</a>
            <div class="dropdown-menu">
                <a class="dropdown-item" href="${pageContext.request.contextPath}/teacher/addTeacher">Add teacher</a>
                <a class="dropdown-item" href="${pageContext.request.contextPath}/teacher/list">List</a>
            </div>
        </li>
    </ul>
</nav>
<h1>
    Welcome to my payroll app

</h1>


<p>
    <a href="${pageContext.request.contextPath}/teacher/list" class="page-link">teachers list</a>
</p>

<p>
    <a href="${pageContext.request.contextPath}/overtime/add/1" class="page-link">add overtime for teacher with id =1</a>
</p>

<%--<p>--%>
<%--    <a href="${pageContext.request.contextPath}/administrativeEmployee/list">administrative employees list</a>--%>
<%--</p>--%>

<%--<p>--%>
<%--    <a href="${pageContext.request.contextPath}/employee/list">employees list</a>--%>
<%--</p>--%>

<p>
    <a href="${pageContext.request.contextPath}/sickLeave/list/1" class="page-link">sick leaves list for teacher with id = 1</a>
</p>

<p>
    <a href="${pageContext.request.contextPath}/sickLeave/add/1" class="page-link">add sick leave for teacher with id = 1</a>
</p>


<p>
    <a href="${pageContext.request.contextPath}/testForm" class="page-link">testForm</a>
</p>

<p>
    <a href="${pageContext.request.contextPath}/overtime/add/1" class="page-link">add Overtime for emp with id=1</a>
</p>

<p>
    <a href="http://localhost:8080/payrollApp/salary/details/2020/5?employeeId=1" class="page-link">employeeId details may 2020 salary</a>
</p>

<p>
    <a href="http://localhost:8080/payrollApp/salary/list/2020/5" class="page-link">essential salaries may 2020</a>
</p>

<p>
    <form:form action="${pageContext.request.contextPath}/logout" method="POST">
        <input class="btn-primary" type="submit" name="logout" value="logout">
    </form:form>
</p>
</body>
</html>
