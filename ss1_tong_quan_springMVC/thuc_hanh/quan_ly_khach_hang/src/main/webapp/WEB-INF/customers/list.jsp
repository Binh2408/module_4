<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 6/17/2025
  Time: 11:05 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head><title>Danh sách khách hàng</title></head>
<body>
<h2>Danh sách khách hàng</h2>
<table border="1">
  <tr>
    <th>ID</th>
    <th>Name</th>
    <th>Email</th>
    <th>Address</th>
    <th>Action</th>
  </tr>
  <c:forEach var="customer" items="${customers}">
    <tr>
      <td>${customer.idCus}</td>
      <td>${customer.nameCus}</td>
      <td>${customer.emailCus}</td>
      <td>${customer.addressCus}</td>
      <td><a href="customers/detail?id=${customer.idCus}">Xem chi tiết</a></td>
    </tr>
  </c:forEach>
</table>
</body>
</html>