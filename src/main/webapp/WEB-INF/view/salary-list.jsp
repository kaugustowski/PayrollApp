<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>

</head>
<body>

<h2>${employee.firstName} ${employee.lastName}</h2>


<table class="table table-responsive table-bordered">
    <thead class="thead-light">
    <tr>
        <th>Name</th>
        <th>BSalary</th>
        <th>GSalary</th>
        <th>NetSalary</th>
        <th>SContr</th>
        <th>PenContr(E)</th>
        <th>DisContr(E)</th>
    </tr>
    </thead>

    <c:forEach var="salary" items="${salaries}">
    <tr class="d-table-row">
        <td> ${salary.employee.firstName} ${salary.employee.lastName}</td>
        <td> ${salary.baseSalary} </td>
        <td> ${salary.contributionBase} </td>
        <td> ${salary.netSalary} </td>
        <td> ${salary.sicknessContribution} </td>
        <td> ${salary.pensionContributionEmployee}</td>
        <td> ${salary.disabilityContributionEmployee}</td>
    </tr>
    </c:forEach>
</table>

</body>
</html>
