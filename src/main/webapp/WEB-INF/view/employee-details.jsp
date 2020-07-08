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
    <title>Save Teacher</title>
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
<div id="wrapper">
    <div id="header">
        <h2>Teacher form</h2>
    </div>
</div>

<div id="container">
    <h3 class="header">Employee details</h3>

    <h2>${emp.firstName } ${emp.lastName }</h2>

    <table class="table table-responsive table-bordered">
        <tr class="d-table-row">
            <td>
                Pesel:
            </td>
            <td>
                ${emp.pesel}
            </td>
        </tr>
        <tr class="d-table-row">
            <td>
                Data urodzenia:
            </td>
            <td>
                ${emp.birthDate}
            </td>
        </tr>
        <tr class="d-table-row">
            <td>
                Data zatrudnienia:
            </td>
            <td>
                ${emp.employeedOnDate}
            </td>
        </tr>
        <tr class="d-table-row">
            <td>
                Wynagrodzenie zasadnicze:
            </td>
            <td>
                ${emp.baseSalaryString}
            </td>
        </tr>
        <tr class="d-table-row">
            <td>
                Dodatek stażowy:
            </td>
            <td>
                ${emp.seniorityBonusString}
            </td>
        </tr>
        <tr class="d-table-row">
            <td>
                Dodatek funkcyjny:
            </td>
            <td>
                ${emp.functionalBonus}
            </td>
        </tr>
        <tr class="d-table-row">
            <td>
                Dodatek motywacyjny:
            </td>
            <td>
                ${emp.incentivePay}
            </td>
        </tr>
        <tr class="d-table-row">
            <td>
                Aktywny:
            </td>
            <td>
                ${emp.active==true ? "Tak" : "Nie"}
            </td>
        </tr>

    </table>


    <div style="clear: both"></div>

    <p>
        <a href="${pageContext.request.contextPath}">Powrót na stronę główną</a>
    </p>

</div>

<footer class="border-top footer text-muted">
    <div class="footer-copyright text-center">© 2020 - PayrollApp - Copyright:
        <a href="https://github.com/kaugustowski"> Karol Augustowski</a>
    </div>
</footer>

</body>

</html>
