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
                    <a class="nav-link" href="${pageContext.request.contextPath}">Strona główna</a>
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


<div class="container justify-content-center">

    <div class="col-auto">
        <div class="pt-2">
            <input type="text" onkeyup="filterBySearchbar()" id="search" placeholder="Wyszukaj...">
        </div>
        <table id="searchTable" class="table table-bordered table-hover table-striped">
            <thead class="thead-light">
            <tr>
                <th>Imię</th>
                <th>Nazwisko</th>
                <th>Email</th>
                <th class="text-center">Aktywny</th>
                <th class="text-center">Akcje</th>
            </tr>
            </thead>

            <c:forEach var="tempTeacher" items="${teachers}">


                <c:url var="updateLink" value="/teacher/showFormForUpdate">
                    <c:param name="teacherId" value="${tempTeacher.id}"/>
                </c:url>


            <c:url var="addSickLeaveLink" value="/sickLeave/list/${tempTeacher.id}">
            </c:url>

            <c:url var="addOvertimeLink" value="/overtime/list/${tempTeacher.id}">
            </c:url>

            <c:url var="salaryList" value="/salary/list/employee/${tempTeacher.id}">
            </c:url>

            <c:url var="empHistory" value="/history/list/${tempTeacher.id}">
            </c:url>


            <tr class="d-table-row">
                <td> ${tempTeacher.firstName} </td>
                <td> ${tempTeacher.lastName} </td>
                <td> ${tempTeacher.email} </td>
                <td class="text-center"> ${tempTeacher.active==true ? "+" : "-"}</td>

                <td class="text-center">
                    <div class="dropdown">
                        <button class="btn btn-secondary dropdown-toggle bg-success"
                                type="button" id="dropdownMenu1" data-toggle="dropdown"
                                aria-haspopup="true" aria-expanded="false">
                            Akcja
                        </button>
                        <div class="dropdown-menu" aria-labelledby="dropdownMenu1">
                            <a class="dropdown-item" href="${updateLink}">Aktualizuj</a>
                            <a class="dropdown-item" href="${addSickLeaveLink}">Zwolnienia lekarskie</a>

                            <a class="dropdown-item" href="${addOvertimeLink}">Nadgodziny</a>
                            <a class="dropdown-item" href="${salaryList}">Lista płac pracownika</a>
                            <a class="dropdown-item" href="${empHistory}">Historia zatrudnienia</a>
                        </div>
                    </div>
                </td>
            </tr>

        </c:forEach>

        </table>
    </div>
</div>

<footer class="border-top footer text-muted">
    <div class="footer-copyright text-center">© 2020 - PayrollApp - Copyright:
        <a href="https://github.com/kaugustowski"> Karol Augustowski</a>
    </div>
</footer>

</body>
</html>
