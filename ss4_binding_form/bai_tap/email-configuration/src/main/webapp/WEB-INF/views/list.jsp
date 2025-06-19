<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>Settings List</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      margin: 40px;
    }

    h1 {
      margin-bottom: 30px;
    }

    table {
      width: 80%;
      border-collapse: collapse;
    }

    th, td {
      border: 1px solid #ddd;
      padding: 12px;
      text-align: left;
    }

    th {
      background-color: #2196F3;
      color: white;
    }

    tr:nth-child(even) {
      background-color: #f2f2f2;
    }
  </style>
</head>
<body>

<h1>Saved Settings</h1>

<table>
  <thead>
  <tr>
    <th>Language</th>
    <th>Page Size</th>
    <th>Spams Filter</th>
    <th>Signature</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach var="emails" items="${emails}">
    <tr>
      <td><a href="/email/${emails.languages}">${emails.languages}</a></td>
      <td>${emails.pageSize}</td>
      <td><c:if test="${emails.spams}">Enabled</c:if><c:if test="${!emails.spams}">Disabled</c:if></td>
      <td><pre>${emails.signature}</pre></td>
    </tr>
  </c:forEach>
  </tbody>
</table>

</body>
</html>
