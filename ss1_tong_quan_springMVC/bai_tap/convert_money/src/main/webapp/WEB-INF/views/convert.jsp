<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 6/17/2025
  Time: 4:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<html>
<head>
    <title>Chuyển đổi</title>
</head>
<body>
<h1>Chuyển đổi tiền tệ</h1>
<form action="converts" method="get">
    <label>Số tiền:</label>
    <input type="number" step="0.01" name="amount" value="${amount}" required>
    <hr>
    <label>Chuyển đổi:</label>
    <select name="direction">
        <option value="usdToVnd" ${direction == 'usdToVnd' ? 'selected' : ''}>USD -> VNĐ</option>
        <option value="vndToUsd" ${direction == 'vndToUsd' ? 'selected' : ''}>VNĐ -> USD</option>
    </select>
    <hr>
    <button type="submit">Chuyển đổi</button>
</form>

<c:if test="${not empty result}">
    <h3>Kết quả:
        <fmt:formatNumber value="${result}" type="number" groupingUsed="true" maxFractionDigits="2"/>

    </h3>
</c:if>

</body>
</html>
