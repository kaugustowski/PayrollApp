<%--
  Created by IntelliJ IDEA.
  User: Wizyg
  Date: 25.03.2020
  Time: 21:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>

<head>
    <title>Save Administrative Employee</title>
</head>

<body>

<div id="wrapper">
    <div id="header">
        <h2>Administrative Employee form</h2>
    </div>
</div>

<div id="container">
    <h3>Save Administrative Employee</h3>

    <h2>${administrativeEmployee.firstName } ${administrativeEmployee.lastName }</h2>

    <form:form action="${pageContext.request.contextPath}/administrativeEmployee/saveAdministrativeEmployee"
               modelAttribute="administrativeEmployee"
               method="POST">

        <form:hidden path="id"/>

        <table>
            <tbody>
            <tr>
                <td><label>First name:</label></td>
                <td><form:input path="firstName"/></td>
            </tr>

            <tr>
                <td><label>Last name:</label></td>
                <td><form:input path="lastName"/></td>
            </tr>

            <tr>
                <td><label>Email:</label></td>
                <td><form:input path="email"/></td>
            </tr>

            <tr>
                <td><label>Pesel:</label></td>
                <td><form:input path="pesel"/></td>
            </tr>

            <tr>
                <td><label>Functional bonus:</label></td>
                <td><form:input path="functionalBonus"/></td>
            </tr>

            <tr>
                <td><label>Bonus:</label></td>
                <td><form:input path="bonus"/></td>
            </tr>


            <tr>
                <td><label></label></td>
                <td><input type="submit" value="Save" class="save"/></td>
            </tr>


            </tbody>
        </table>


    </form:form>

    <div style="clear: both"></div>

    <p>
        <a href="${pageContext.request.contextPath}/administrativeEmployee/list">Back to List</a>
    </p>

</div>

</body>

</html>
