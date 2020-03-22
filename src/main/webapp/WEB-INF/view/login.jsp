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
    <title>login</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <style>

        body {
            margin: 0;
            padding: 0;
            font-family: sans-serif;
            background: #202020;
        }

        .login-box {
            width: 280px;
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            color: white;
        }

        .login-box h1 {
            float: left;
            font-size: 40px;
            border-bottom: 6px solid #4caf50;
            margin-bottom: 50px;
            padding: 13px 0;
        }

        .textbox {
            width: 100%;
            overflow: hidden;
            font-size: 20px;
            padding: 8px 0;
            margin: 8px 0;
            border-bottom: 1px solid #4caf50;
        }

        .textbox i {
            width: 26px;
            float: left;
            text-align: center;
        }

        .textbox input {
            border: none;
            outline: none;
            background: none;
            color: white;
            font-size: 18px;
            width: 80%;
            float: left;
            margin: 0 10px;
        }

        .btn {
            width: 100%;
            background: none;
            border: 2px solid #4caf50;
            color: white;
            padding: 5px;
            font-size: 18px;
            cursor: pointer;
            margin: 12px 0;
        }

        .btn:hover {
            border-color: #4cffff;
        }

        .failed {
            width: 100%;
            color: red;
            font-size: 18px;

        }

        .logout {
            width: 100%;
            color: green;
            font-size: 18px;

        }


    </style>
</head>
<body>

<form:form name='f' action="${pageContext.request.contextPath}/userAuth" method='POST'>


    <div class="login-box">
        <h1>Login</h1>


        <div class="textbox">
            <i class="fa fa-user icon"></i>
            <input type="text" placeholder="Username" name="username" value="">
        </div>
        <div class="textbox">
            <i class="fa fa-lock icon"></i>
            <input type='password' placeholder="Password" name="password"/>
        </div>
        <input class="btn" name="Sign in" type="submit" value="submit"/>

        <c:if test="${param.error != null}">
            <div>
                <i class="failed">Sorry! You entered invalid username/password.</i>
            </div>
        </c:if>


        <c:if test="${param.logout != null}">
            <div>
                <i class="logout">Logged out succesfully.</i>
            </div>
        </c:if>

    </div>


</form:form>

</body>
</html>
