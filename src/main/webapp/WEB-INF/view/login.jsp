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

    <style>
        @import "https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css";

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

    </style>
</head>
<body>

<h1>Login</h1>
<form name='f' action="${pageContext.request.contextPath}/userAuth" method='POST'>

    <div class="login-box">
        <h1>Login</h1>
        <div class="textbox">
            <i class="fas fa-user" aria-hidden="true"></i>
            <input type="text" placeholder="Username" name="username" value="">
        </div>
        <div class="textbox">
            <i class="fas fa-lock" aria-hidden="true"></i>
            <input type='password' placeholder="Password" name="password"/>
        </div>
        <input class="btn" name="Sign in" type="submit" value="submit"/>
    </div>


</form>

</body>
</html>
