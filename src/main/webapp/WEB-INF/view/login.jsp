<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Wizyg
  Date: 20.03.2020
  Time: 19:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="<c:url value="/resources/css/login.css" />">

</head>
<body>

<form:form name='f' action="${pageContext.request.contextPath}/userAuth" method='POST'>


    <div class="login-box">
        <h1>Login</h1>


        <div class="textbox">
            <i class="fa fa-user icon"></i>
            <input type="text" placeholder="Nazwa użytkownika" name="username" value="">
        </div>
        <div class="textbox">
            <i class="fa fa-lock icon"></i>
            <input type='password' placeholder="Hasło" name="password"/>
        </div>
        <input class="btn" name="submit" type="submit" value="Zaloguj"/>

        <c:if test="${param.error != null}">
            <div>
                <i class="failed">Wprowadzono błędną nazwę użytkownika/hasło!.</i>
            </div>
        </c:if>


        <c:if test="${param.logout != null}">
            <div>
                <i class="logout">Wylogowano pomyślnie.</i>
            </div>
        </c:if>

    </div>


</form:form>

</body>
</html>
