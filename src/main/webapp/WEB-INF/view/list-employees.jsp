<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Wizyg
  Date: 25.03.2020
  Time: 21:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
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
                        <a class="nav-link" href=${pageContext.request.contextPath}/employee/list">Pracownicy</a>
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

<table>
    <tr>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Email</th>
    </tr>


    <c:forEach var="employee" items="${employees}">


        <%--        <c:url var="updateLink" value="/teacher/showFormForUpdate">--%>
        <%--            <c:param name="employeeId" value="${employee.id}"/>--%>
        <%--        </c:url>--%>


        <%--        <c:url var="deleteLink" value="/teacher/delete">--%>
        <%--            <c:param name="teacherId" value="${tempTeacher.id}"/>--%>
        <%--        </c:url>--%>

        <tr>
            <td> ${employee.firstName} </td>
            <td> ${employee.lastName} </td>
            <td> ${employee.email} </td>

                <%--            <td>--%>
                <%--                <a href="${updateLink}">Update</a>--%>
                <%--                |--%>
                <%--                <a href="${deleteLink}"--%>
                <%--                   onclick="if (!(confirm('Are you sure you want to delete this teacher?'))) return false">Delete</a>--%>
                <%--            </td>--%>

        </tr>

    </c:forEach>

    <%--    <c:url var="calculateLink" value="/teacher/calculate">--%>
    <%--                    <c:param name="teacherId" value="${tempTeacher.id}"/>--%>
    <%--    </c:url>--%>


    <%--    <button onclick="${calculateLink}"> Calculate </button>--%>
</table>


</body>
</html>
