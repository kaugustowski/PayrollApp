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
</head>
<body>



<table>
    <tr>
        <th>Name</th>
        <th>Base Salary</th>
        <th>Gross Salary</th>
        <th>Net Salary</th>
        <th>Sickness Contribution</th>
        <th>Pension Contribution(E)</th>
        <th>Disability Contribution(E)</th>
    </tr>
    <c:forEach var="salary" items="${salaries}">
    <tr>
        <td>${salary.employee.firstName} ${salary.employee.lastName}</td>
        <td> ${salary.baseSalary} </td>
        <td> ${salary.contributionBase} </td>
        <td> ${salary.netSalary} </td>
        <td> ${salary.sicknessContribution} </td>
        <td>${salary.pensionContributionEmployee}</td>
        <td>${salary.disabilityContributionEmployee}</td>

    </tr>

</c:forEach>
</table>

</body>
</html>
