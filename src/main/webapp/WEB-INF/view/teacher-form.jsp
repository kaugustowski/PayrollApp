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
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css" />">
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
    <script src="<c:url value="/resources/js/script1.js" />" rel="stylesheet" defer></script>

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

<div id="form-container">
    <h3>Zapisz nauczyciela</h3>

    <h2 class="center">${teacher.firstName } ${teacher.lastName }</h2>

    <form:form action="${pageContext.request.contextPath}/teacher/saveTeacher" modelAttribute="teacher" method="POST">

        <form:hidden path="id"/>

        <div class="form-row">
            <div class="column">
                <div class="form-group">
                    <label for="firstName" class="col-sm-2 col-form-label">Imię:</label>
                    <div class="col-sm-10">
                        <form:input path="firstName" id="firstName"/>
                        <form:errors path="firstName" cssClass="ui-state-error-text"/>
                    </div>
                </div>


                <div class="form-group">
                    <label for="lastName" class="col-sm-2 col-form-label">Nazwisko:</label>
                    <div class="col-sm-10">
                        <form:input path="lastName" id="lastName"/>
                        <form:errors path="lastName" cssClass="ui-state-error-text"/>
                    </div>
                </div>

                <div class="form-group">
                    <label for="email" class="col-sm-2 col-form-label">Email:</label>
                    <div class="col-sm-10">
                        <form:input path="email" id="email"/>
                        <form:errors path="email" cssClass="ui-state-error-text"/>
                    </div>
                </div>

                <div class="form-group">
                    <label for="pesel" class="col-sm-2 col-form-label">Pesel:</label>
                    <div class="col-sm-10">
                        <form:input path="pesel" id="pesel"/>
                        <form:errors path="pesel" cssClass="ui-state-error-text"/>
                    </div>
                </div>

                <div class="form-group">
                    <label for="birthDate" class="col-sm-2 col-form-label">Birth date:</label>
                    <div class="col-sm-10">
                        <form:input id="birthDate" type="date" path="birthDate"/>
                    </div>
                </div>

                <div class="form-group">
                    <label for="teacher_type" class="col-sm-2 col-form-label">Stopień awansu zawodowego:</label>
                    <div class="col-sm-10">
                        <form:select path="teacherType" id="teacher_type">
                            <c:forEach var="teacherType" items="${teacherTypeValues}">
                                <option value="${teacherType}" ${teacherType == teacher.teacherType ? 'selected="selected"' : ''}>${teacherType.name()}</option>
                            </c:forEach>

                        </form:select>
                    </div>
                </div>

                <div class="form-group">
                    <label for="education" class="col-sm-2 col-form-label">Wykształcenie:</label>
                    <div class="col-sm-10">
                        <form:select path="education">
                            <c:forEach var="education" items="${educationValues}">
                                <option value="${education}" ${education == teacher.education ? 'selected="selected"' : ''}>${education.name()}</option>
                            </c:forEach>

                        </form:select>
                    </div>
                </div>
            </div>

            <div class="column">

                <div class="form-group">
                    <label for="funcBonus" class="col-form-label">Dodatek funkcyjny:</label>
                    <div class="col-sm-10">
                        <form:input id="funcBonus" type="number" step="0.01" path="functionalBonusString"/>
                        <form:errors path="functionalBonus" cssClass="ui-state-error-text"/>
                    </div>
                </div>

                <div class="form-group">
                    <label for="seniorityBonus" class="col-form-label">Dodatek stażowy:</label>
                    <div class="col-sm-10">
                        <form:input id="seniorityBonus" type="number" step="0.01" path="seniorityBonusString"/>
                        <form:errors path="seniorityBonus" cssClass="ui-state-error-text"/>
                    </div>
                </div>

                <div class="form-group">
                    <label for="incPay" class="col-form-label">Dodatek motywacyjny:</label>
                    <div class="col-sm-10">
                        <form:input id="incPay" type="number" step="0.01" path="incentivePayString"/>
                        <form:errors path="incentivePay" cssClass="ui-state-error-text"/>
                    </div>
                </div>


                <div class="form-group">
                    <label for="employeedOnDate" class="col-form-label">Data zatrudnienia:</label>
                    <div class="col-sm-10">
                        <form:input id="employeedOnDate" type="date" path="employeedOnDate"/>
                    </div>
                </div>

                <div class="form-group">
                    <label for="active" class="col-form-label">Aktywny:</label>
                    <div class="col-sm-10">
                        <form:checkbox id="active" path="active"/>
                    </div>
                </div>

                <div class="form-group">
                    <label for="extraExpenses" class="col-form-label">Podwyższone KUP:</label>
                    <div class="col-sm-10">
                        <form:checkbox id="extraExpenses" path="allowedForExtraTaxDeductibleExpenses"/>
                    </div>
                </div>


            </div>
        </div>

        <div class="form-group row">
            <div class="col-sm-10 offset-sm-2">
                <button type="submit" class="btn btn-primary">Dodaj pracownika</button>
            </div>
        </div>


    </form:form>

    <div style="clear: both"></div>

    <p>
        <a href="${pageContext.request.contextPath}/teacher/list">Powrót do listy</a>
    </p>

</div>

</body>

</html>
