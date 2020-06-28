<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Wizyg
  Date: 25.03.2020
  Time: 21:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <meta charset="utf-8"/>
    <title>Title</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
    <script src="<c:url value="/resources/js/script1.js" />" rel="stylesheet" defer></script>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css" />">
</head>
<body>
<nav class="navbar sticky-top navbar-dark bg-transparent">
    <div class="d-flex">
        <div class="p-2 w-100 ">
            <ul class="nav nav-tabs">
                <li>
                    <a class="nav-link active" href="${pageContext.request.contextPath}">Strona główna</a>
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
            </ul>
        </div>

        <div class="p-2 flex-shrink d-inline pull-right">
            <form:form class="navbar-right" action="${pageContext.request.contextPath}/logout" method="POST">
                <input class="nav-link" type="submit" name="logout" value="Wyloguj">
            </form:form>
        </div>


    </div>
</nav>

<div class="container">
    <table class="table table-responsive table-bordered table-hover table-striped">
        <thead class="thead-light">
        <tr>
            <th>Imię</th>
            <th>Nazwisko</th>
            <th>Email</th>
            <th>Aktywny</th>
            <th>Akcje</th>
        </tr>
        </thead>


        <c:forEach var="tempTeacher" items="${teachers}">


            <c:url var="updateLink" value="/teacher/showFormForUpdate">
                <c:param name="teacherId" value="${tempTeacher.id}"/>
            </c:url>


            <c:url var="deleteLink" value="/teacher/delete">
                <c:param name="teacherId" value="${tempTeacher.id}"/>
            </c:url>

            <c:url var="addSickLeaveLink" value="/sickLeave/add/${tempTeacher.id}">
            </c:url>

            <c:url var="addOvertimeLink" value="/overtime/add/${tempTeacher.id}">
            </c:url>

            <c:url var="salaryList" value="/salary/list/employee/${tempTeacher.id}">
            </c:url>

            <c:url var="empHistory" value="/history/list/${tempTeacher.id}">
            </c:url>


            <tr class="d-table-row">
                <td> ${tempTeacher.firstName} </td>
                <td> ${tempTeacher.lastName} </td>
                <td> ${tempTeacher.email} </td>
                <td> ${tempTeacher.active==true ? "+" : "-"}</td>

                <td>
                    <div class="dropdown">
                        <button class="btn btn-secondary dropdown-toggle bg-success"
                                type="button" id="dropdownMenu1" data-toggle="dropdown"
                                aria-haspopup="true" aria-expanded="false">
                            Akcja
                        </button>
                        <div class="dropdown-menu" aria-labelledby="dropdownMenu1">
                            <a class="dropdown-item" href="${updateLink}">Aktualizuj</a>
                            <a class="dropdown-item" href="${addSickLeaveLink}">Dodaj zwolnienie lekarskie</a>
                            <a class="dropdown-item" href="${deleteLink}"
                               onclick="if (!(confirm('Czy na pewno chcesz usunąć tego pracownika?' +
                            ' Spowoduje to usunięcie historii wypłat z nim powiązanych! Rozważ zmianę statusu pracownika na nieaktywny'))) return false">Usuń
                                pracownika</a>
                            <a class="dropdown-item" href="${addOvertimeLink}">Dodaj nadgodziny</a>
                            <a class="dropdown-item" href="${salaryList}">Lista płac pracownika</a>
                            <a class="dropdown-item" href="${empHistory}">Historia zatrudnienia</a>
                        </div>
                    </div>
                </td>
            </tr>

        </c:forEach>

    </table>
</div>


</body>
</html>
