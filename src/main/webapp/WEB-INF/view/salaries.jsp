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
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
<style>
    #dropdownMenu1{
        height: 80%;
    }
    td{
        text-align: center;
        vertical-align: middle;
    }
</style>
</head>
<body>


<table class="table table-responsive table-bordered">
    <thead class="thead-light">
    <tr>
        <th>Year</th>
        <th>Month</th>
        <th>Actions</th>
    </tr>
    </thead>


    <c:forEach var="month" items="${salaryMonths}">


        <c:url var="showList" value="/salary/list/${month.year}/${month.month}">

        </c:url>


        <c:url var="recalculate" value="/salary/calculateSalaries/${month.year}/${month.month}">

        </c:url>



        <tr class="d-table-row">
            <td> ${month.year} </td>
            <td> ${month.month} </td>
            <td>

                <div class="dropdown">
                    <button class="btn btn-secondary dropdown-toggle bg-success"
                            type="button" id="dropdownMenu1" data-toggle="dropdown"
                            aria-haspopup="true" aria-expanded="false">
                        Action
                    </button>
                    <div class="dropdown-menu" aria-labelledby="dropdownMenu1">
                        <a class="dropdown-item" href="${showList}">List</a>
                        <a class="dropdown-item" href="${recalculate}">Recalculate</a>
                    </div>
                </div>
               </td>
        </tr>

    </c:forEach>

</table>


</body>
</html>
