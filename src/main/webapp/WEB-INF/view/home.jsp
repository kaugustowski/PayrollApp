<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>

</head>
<body>


<nav class="navbar sticky-top navbar-dark bg-transparent">
    <div class="d-flex">
        <div class="p-2 w-100 ">
            <ul class="nav nav-tabs">
                <li>
                    <a class="nav-link active" href="#">Strona główna</a>
                </li>
                <c:if test="${pageContext.request.isUserInRole('PAYROLL_SPECIALIST')}">
                    <li class="nav-item">
                        <a class="nav-link" href=${pageContext.request.contextPath}/employee/list">Pracownicy</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" data-toggle="dropdown"
                           href="#" role="button" aria-haspopup="true" aria-expanded="false">
                            Nauczyciele</a>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/teacher/addTeacher">Dodaj
                                nauczyciela</a>
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/teacher/list">Lista nauczycieli</a>
                        </div>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" data-toggle="dropdown"
                           href="#" role="button" aria-haspopup="true" aria-expanded="false">
                            Płace</a>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/salary/essentialList">Zasadnicze</a>
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/salary/overtimeList">Nadgodziny</a>
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/salary/list">Wszystkie</a>
                        </div>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/salary/createPayroll">Nowa lista
                            płac</a>
                    </li>
                </c:if>
                <c:if test="${pageContext.request.isUserInRole('USER')}">
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/salary/my">Moje płace</a>
                    </li>
                </c:if>

                <c:if test="${pageContext.request.isUserInRole('ADMIN')}">
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/admin/register">Dodaj
                            użytkownika</a>
                    </li>
                </c:if>
            </ul>
        </div>

        <div class="p-2 flex-shrink d-inline pull-right">
            <form:form class="navbar-right" action="${pageContext.request.contextPath}/logout" method="POST">
                <input class="nav-link" type="submit" name="logout" value="Wyloguj">
            </form:form>
        </div>

    </div>

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
