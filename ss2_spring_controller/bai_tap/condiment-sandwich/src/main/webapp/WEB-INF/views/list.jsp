<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 6/18/2025
  Time: 3:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
</head>
<body>

<h1>Sandwich Condiments</h1>

<form action="save">
  <input type="checkbox" id="lettuce" name="condiment" value="Lettuce"><span>Lettuce</span>
  <input type="checkbox" id="tomato" name="condiment" value="Tomato"><span>Tomato</span>
  <input type="checkbox" id="mustard" name="condiment" value="Mustard"><span>Mustard</span>
  <input type="checkbox" id="sprouts" name="condiment" value="Sprouts"><span>Sprouts</span>
  <hr>
  <input type="submit" value="Save">
</form>
<c:if test="${not empty condiment}">
  <h3>Selected:</h3>
  <ul>
    <c:forEach var="item" items="${condiment}">
      <li>${item}</li>
    </c:forEach>
  </ul>
</c:if>

</body>
</html>
