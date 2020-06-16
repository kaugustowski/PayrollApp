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

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>

</head>

<body>

<div id="wrapper">
    <div id="header">
        <h2>Teacher form</h2>
    </div>
</div>

<div id="container">
    <h3>Save Teacher</h3>

    <h2>${teacher.firstName } ${teacher.lastName }</h2>

    <form:form action="${pageContext.request.contextPath}/teacher/saveTeacher" modelAttribute="teacher" method="POST">

        <form:hidden path="id"/>

        <div class="form-group row">
            <label for="firstName" class="col-sm-2 col-form-label">First name:</label>
            <div class="col-sm-10">
                <form:input path="firstName" id="firstName"/>
            </div>
        </div>


        <div class="form-group row">
            <label for="lastName" class="col-sm-2 col-form-label">Last name:</label>
            <div class="col-sm-10">
                <form:input path="lastName" id="lastName"/>
            </div>
        </div>

        <div class="form-group row">
            <label for="email" class="col-sm-2 col-form-label">Email:</label>
            <div class="col-sm-10">
                <form:input path="email" id="email"/>
            </div>
        </div>

        <div class="form-group row">
            <label for="pesel" class="col-sm-2 col-form-label">Pesel:</label>
            <div class="col-sm-10">
                <form:input path="pesel" id="pesel"/>
            </div>
        </div>

        <div class="form-group row">
            <label for="teacher_type" class="col-sm-2 col-form-label">Teacher type:</label>
            <div class="col-sm-10">
                <form:select path="teacherType" id="teacher_type">
                    <c:forEach var="teacherType" items="${teacherTypeValues}">
                        <option value="${teacherType}" ${teacherType == teacher.teacherType ? 'selected="selected"' : ''}>${teacherType.name()}</option>
                    </c:forEach>

                </form:select>
            </div>
        </div>

        <div class="form-group row">
            <label for="education" class="col-sm-2 col-form-label">Education:</label>
            <div class="col-sm-10">
                <form:select path="education">
                    <c:forEach var="education" items="${educationValues}">
                        <option value="${education}" ${education == teacher.education ? 'selected="selected"' : ''}>${education.name()}</option>
                    </c:forEach>

                </form:select>
            </div>
        </div>

        <div class="form-group row">
            <label for="funcBonus" class="col-sm-2 col-form-label">Functional bonus:</label>
            <div class="col-sm-10">
                <form:input id="funcBonus" type="number" step="0.01" path="functionalBonusString"/>
            </div>
        </div>

        <div class="form-group row">
            <label for="seniorityBonus" class="col-sm-2 col-form-label">Seniority bonus:</label>
            <div class="col-sm-10">
                <form:input id="seniorityBonus" type="number" step="0.01" path="seniorityBonusString"/>
            </div>
        </div>

        <div class="form-group row">
            <label for="incPay" class="col-sm-2 col-form-label">Incentive pay:</label>
            <div class="col-sm-10">
                <form:input id="incPay" type="number" step="0.01" path="incentivePayString"/>
            </div>
        </div>

        <div class="form-group row">
            <label for="birthDate" class="col-sm-2 col-form-label">Birth date:</label>
            <div class="col-sm-10">
                <form:input id="birthDate" type="date" path="birthDate"/>
            </div>
        </div>

        <div class="form-group row">
            <label for="employeedOnDate" class="col-sm-2 col-form-label">Employed on date:</label>
            <div class="col-sm-10">
                <form:input id="employeedOnDate" type="date" path="employeedOnDate"/>
            </div>
        </div>

        <div class="form-group row">
            <label for="active" class="col-sm-2 col-form-label">Is Active:</label>
            <div class="col-sm-10">
                <form:checkbox id="active" path="active"/>
            </div>
        </div>

        <div class="form-group row">
            <label for="extraExpenses" class="col-sm-2 col-form-label">Extra tax deductible expenses:</label>
            <div class="col-sm-10">
                <form:checkbox id="extraExpenses" path="allowedForExtraTaxDeductibleExpenses"/>
            </div>
        </div>



            <div class="form-group row">
                <div class="col-sm-10 offset-sm-2">
                    <button type="submit" class="btn btn-primary">Sign in</button>
                </div>
            </div>


    </form:form>

    <div style="clear: both"></div>

    <p>
        <a href="${pageContext.request.contextPath}/teacher/list">Back to List</a>
    </p>

</div>

</body>

</html>
