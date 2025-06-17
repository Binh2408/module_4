<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 6/17/2025
  Time: 11:35 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Edit Customer</title>
</head>
<body>
<h2>Customer Information</h2>

<form action="${pageContext.request.contextPath}/customers/update" method="get">
    <fieldset>
        <legend>Customer Information</legend>
        <table>
            <tr>
                <td>Id</td>
                <td>${customer.idCus}</td>
                <input type="hidden" name="idCus" value="${customer.idCus}" />
            </tr>
            <tr>
                <td>Name</td>
                <td><input type="text" name="nameCus" value="${customer.nameCus}"/></td>
            </tr>
            <tr>
                <td>Email</td>
                <td><input type="email" name="emailCus" value="${customer.emailCus}"/></td>
            </tr>
            <tr>
                <td>Address</td>
                <td><input type="text" name="addressCus" value="${customer.addressCus}"/></td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" value="Update"/>
                </td>
            </tr>
        </table>
    </fieldset>
</form>

<br/>
<a href="${pageContext.request.contextPath}/customers">Back to list</a>
</body>
</html>
