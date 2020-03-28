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
<table>
    <tr>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Email</th>
        <th>Action</th>
    </tr>

    <!-- loop over and print our customers -->
    <c:forEach var="tempCustomer" items="${customers}">

        <!-- construct an "update" link with customer id -->
        <c:url var="updateLink" value="/customer/showFormForUpdate">
            <c:param name="customerId" value="${tempCustomer.id}"/>
        </c:url>

        <!-- construct an "delete" link with customer id -->
        <c:url var="deleteLink" value="/customer/delete">
            <c:param name="customerId" value="${tempCustomer.id}"/>
        </c:url>

        <tr>
            <td> ${tempCustomer.firstName} </td>
            <td> ${tempCustomer.lastName} </td>
            <td> ${tempCustomer.email} </td>

            <td>
                <!-- display the update link -->
                <a href="${updateLink}">Update</a>
                |
                <a href="${deleteLink}"
                   onclick="if (!(confirm('Are you sure you want to delete this customer?'))) return false">Delete</a>
            </td>

        </tr>

    </c:forEach>

</table>
</body>
</html>
