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
    <title>Save Customer</title>
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

        <table>
            <tbody>

            <tr>
                <td><label>Choose a teacher type:</label></td>
                <td>
                    <form:select path="teacherType">
                        <option value="INTERN">Intern</option>
                        <option value="CONTRACT">Contract</option>
                        <option value="APPOINTED">Appointed</option>
                        <option value="CERTIFIED">Certified</option>
                    </form:select>
                </td>
            </tr>

            <tr>
                <td><label>Choose an education level:</label></td>
                <td>
                    <form:select path="education">
                        <option value="HIGHER_WITH_PEDAGOGIC_PREP">Higher with pedagogic preparation</option>
                        <option value="HIGHER_WITHOUT_PEDAGOGIC_PREP">Higher without pedagogic preparation</option>
                        <option value="OTHER">Other</option>
                    </form:select>
                </td>
            </tr>

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
                <td><label>Incentive pay:</label></td>
                <td><form:input path="incentivePay"/></td>
            </tr>

            <tr>
                <td><label>Seniority bonus:</label></td>
                <td><form:input path="seniorityBonus"/></td>
            </tr>


            <tr>
                <td><label></label></td>
                <td><input type="submit" value="Save" class="save" onclick="${teacher.setBaseSalary()}"/></td>
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
