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
    <title>Save Customer</title>
</head>

<body>

<div id="wrapper">
    <div id="header">
        <h2>Test form</h2>
    </div>
</div>

<div id="container">
    <h3>Save Test</h3>


    <form:form action="${pageContext.request.contextPath}/saveTest" modelAttribute="testObj" method="POST">


        <table>
            <tbody>


            <tr>
                <td><label>test value:</label></td>
                <td><form:input path="testValue"/></td>
            </tr>


            <tr>
                <td><label>date:</label></td>
                <td><form:input type="date" path="date"/></td>
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
        <a href="${pageContext.request.contextPath}/teacher/list">Back to List</a>
    </p>

</div>

</body>

</html>
