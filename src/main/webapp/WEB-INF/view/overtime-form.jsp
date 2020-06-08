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
    <title>Overtime</title>
</head>

<body>

<div id="wrapper">
    <div id="header">
        <h2>Overtime form</h2>
    </div>
</div>

<div id="container">
    <h3>add overtime hours</h3>

    <h2>${employee.firstName } ${employee.lastName }</h2>

    <form:form action="${pageContext.request.contextPath}/overtime/save/${employee.id}" modelAttribute="overtime"
               method="POST">

        <form:hidden path="id"/>

        <table>
            <tbody>

            <tr>
                <td><label>month:</label></td>
                <td><form:select type="date" path="month">
                        <option value=''>--Select Month--</option>
                        <option selected value='1'>January</option>
                        <option value='2'>February</option>
                        <option value='3'>March</option>
                        <option value='4'>April</option>
                        <option value='5'>May</option>
                        <option value='6'>June</option>
                        <option value='7'>July</option>
                        <option value='8'>August</option>
                        <option value='9'>September</option>
                        <option value='10'>October</option>
                        <option value='11'>November</option>
                        <option value='12'>December</option>
                    </form:select></td>

            </tr>

            <tr>
                <td><label>year:</label></td>
                <td><form:select id="year" type="date" path="year">


                </form:select></td>
            </tr>

            <script>
                const start = 2010;
                const end = new Date().getFullYear();
                let options = "";
                for(let year = start ; year <=end; year++){
                    options += "<option value=\""+year+"\">"+ year +"</option>";
                }
                document.getElementById("year").innerHTML = options;
            </script>


            <tr>
                <td><label>number of hours:</label></td>
                <td><form:input path="numberOfOverTimeHoursInCurrentMonth"/></td>
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
