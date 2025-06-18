<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 6/18/2025
  Time: 3:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Calculator</h1>
<form action="calculator">
    <input type="number" name="num1" value="${num1}"> <input type="number" name="num2" value="${num2}"> <br> <br>
    <button name="operator" value="+" type="submit">Addition(+)</button>
    <button name="operator" value="-" type="submit">Subtraction(-)</button>
    <button name="operator" value="x" type="submit">Multiplication(X)</button>
    <button name="operator" value="/" type="submit">Division(/)</button>
</form>
<c:if test="${not empty result}">
    <h2>Result ${operationName}: ${result}</h2>
</c:if>
</body>
</html>
