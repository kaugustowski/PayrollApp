<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Wizyg
  Date: 18.04.2020
  Time: 12:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Historia zatrudnienia</title>


</head>
<body>

<div class="container sticky-top" id="nav-container">
    <nav class="navbar navbar-dark navbar-expand-md bg-dark ">
        <button class="navbar-toggler" type="button" data-toggle="collapse"
                data-target="#navbarColor01" aria-controls="navbarColor01" aria-expanded="false"
                aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarColor01">

            <ul class="navbar-nav mr-auto">
                <li>
                    <a class="nav-link" href="#">Strona główna</a>
                </li>
                <c:if test="${pageContext.request.isUserInRole('PAYROLL_SPECIALIST')}">
                    <li class="nav-item">
                        <a class="nav-link" href=${pageContext.request.contextPath}/employee/list>Pracownicy</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" data-toggle="dropdown"
                           href="#" role="button" aria-haspopup="true" aria-expanded="false">
                            Nauczyciele</a>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/teacher/addTeacher">Dodaj
                                nauczyciela</a>
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/teacher/list">Lista
                                nauczycieli</a>
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

                <li class="nav-item">

                </li>

            </ul>
            <form:form cssClass="form-inline my-2" action="${pageContext.request.contextPath}/logout" method="POST">
                <input class="btn btn-outline-success my-2 my-sm-0" type="submit" name="logout" value="Wyloguj">
            </form:form>

        </div>

    </nav>

</div>
<h1>Historia zatrudnienia</h1>

<h2>${employee.firstName} ${employee.lastName}</h2>

<table>
    <thead class="thead-light">
    <tr class="table table-responsive table-bordered">
        <th>Nazwa placówki</th>
        <th>Data rozpoczęcia</th>
        <th>Data zakończenia</th>
        <th>Dni na urlopie bezpłatnym</th>
    </tr>
    </thead>


    <c:forEach var="eh" items="${history}">
        <tr class="d-table-row">
            <td>${eh.institutionName}</td>
            <td>${eh.startDate}</td>
            <td>${eh.endDate}</td>
            <td>${eh.numberOfDaysOnUnpaidLeave}</td>
        </tr>
    </c:forEach>

    <a class="btn btn-primary" href="${pageContext.request.contextPath}/history/add/${employeeId}" class="ui-button">Add
        another work experience</a>

</table>


</body>
</html>
