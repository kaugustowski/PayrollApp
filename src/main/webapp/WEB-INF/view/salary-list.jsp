<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Wizyg
  Date: 28.05.2020
  Time: 19:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Salary list</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css" />">
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
    <script src="<c:url value="/resources/js/script1.js" />" rel="stylesheet" defer></script>

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
<div class="container">
    <h2>${employee.firstName} ${employee.lastName} </h2>

    <c:forEach var="salary" items="${salaries}">
        <div class="grid-container border border-primary">
            <div class="Name"><b>${salary.employee.firstName} ${salary.employee.lastName},
                Pesel: ${salary.employee.pesel} - ${salary.month}/${salary.year}
                - ${salary['class'].simpleName == 'EssentialSalary'? 'Wynagrodzenie zasadnicze': 'Wynagrodzenie za nadgodziny'} </b>
            </div>
            <div class="components border">
                Podstawa:<br/>
                Dod. funkcyjny:<br/>
                Dod. motywacyjny:<br/>
                Dod. stażowy:<br/>
                <c:if test="${salary['class'].simpleName == 'EssentialSalary'}">
                    Wyn. chor.:<br/>
                    Zas. chor:<br/>
                </c:if>
                <c:if test="${salary['class'].simpleName == 'OvertimeSalary'}">
                    Liczba godzin:<br/>
                    Stawka godzinowa:<br/>
                </c:if>
                <b>Suma brutto:</b><br/>
            </div>
            <div class="comp-values border pr-4 text-right">
                    ${salary.baseSalaryString}<br/>
                    ${salary.functionalBonusString}<br/>
                    ${salary.incentivePayString}<br/>
                    ${salary.seniorityBonusString}<br/>
                <c:if test="${salary['class'].simpleName == 'EssentialSalary'}">
                    ${salary.sickPayString}<br/>
                    ${salary.sicknessAllowanceString}<br/>
                </c:if>
                <c:if test="${salary['class'].simpleName == 'OvertimeSalary'}">
                    ${salary.numberOfOvertimeHours}<br/>
                    ${salary.overtimeHourRateString}<br/>
                </c:if>
                <b>${salary.grossSalaryString}</b><br/>
            </div>
            <div class="employee-contribution  ">
                Skł. emeryt. prac.:<br/>
                Skł. rentowa prac.:<br/>
                Skł. chorob. prac.:<br/>
                Skł. zdrow.:<br/>
                Skł. zdrow. odl.:<br/>
                <c:if test="${salary['class'].simpleName == 'EssentialSalary'}">
                    KUP:<br/>
                </c:if>
                Zaliczka na podatek:<br/>
            </div>
            <div class="payer-contr border">
                Skł. emeryt. płat.:<br/>
                Skł. rentowa płat.:<br/>
                Skł. wypadkowa:<br/>
                Fund. pracy:<br/>
            </div>
            <div class="ec-values border pr-4 text-right">
                    ${salary.pensionContributionEmployeeString}<br/>
                    ${salary.disabilityContributionEmployeeString}<br/>
                    ${salary.sicknessContributionString}<br/>
                    ${salary.healthcareContributionString}<br/>
                    ${salary.healthcareContributionDeductionString}<br/>
                <c:if test="${salary['class'].simpleName == 'EssentialSalary'}">
                    ${salary.taxDeductibleExpensesString}<br/>
                </c:if>


                    ${salary.incomeTaxAdvanceString}<br/>


            </div>
            <div class="pc-values border pr-4 text-right">
                    ${salary.pensionContributionPayerString}<br/>
                    ${salary.disabilityContributionPayerString}<br/>
                    ${salary.accidentInsuranceContributionString}<br/>
                    ${salary.laborFundString}<br/>
            </div>
            <div class="net border">
                <b>Suma netto:</b>
            </div>
            <div class="net-value border pr-4 text-right">
                <b>${salary.netSalaryString}</b>
            </div>
        </div>
    </c:forEach>

</div>

<footer class="border-top footer text-muted">
    <div class="footer-copyright text-center">© 2020 - PayrollApp - Copyright:
        <a href="https://github.com/kaugustowski"> Karol Augustowski</a>
    </div>
</footer>

</body>
</html>
