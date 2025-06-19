<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>Email Settings List</title>
  <style>
    body {
      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
      background-color: #f9f9f9;
      padding: 40px;
    }

    h1 {
      margin-bottom: 20px;
      color: #333;
    }

    .btn-add {
      display: inline-block;
      margin-bottom: 20px;
      padding: 8px 16px;
      background-color: #4CAF50;
      color: white;
      text-decoration: none;
      border-radius: 4px;
      font-weight: bold;
    }

    .btn-add:hover {
      background-color: #45a049;
    }

    table {
      width: 100%;
      border-collapse: collapse;
      background-color: white;
      box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
    }

    th, td {
      padding: 12px 16px;
      border-bottom: 1px solid #ddd;
      text-align: left;
    }

    th {
      background-color: #2196F3;
      color: white;
    }

    tr:hover {
      background-color: #f1f1f1;
    }

    td a {
      color: #2196F3;
      text-decoration: none;
      font-weight: bold;
    }

    td a:hover {
      text-decoration: underline;
    }

    pre {
      margin: 0;
      font-family: inherit;
      white-space: pre-wrap;
      word-wrap: break-word;
    }
  </style>
</head>
<body>

<h1>Email Settings</h1>

<a href="/email/add" class="btn-add">+ Add New Configuration</a>

<table>
  <thead>
  <tr>
    <th>#</th>
    <th>Language</th>
    <th>Page Size</th>
    <th>Spam Filter</th>
    <th>Signature</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach var="emails" items="${emails}" varStatus="idx">
    <tr>
      <td>${idx.count}</td>
      <td><a href="/email/edit/${emails.id}">${emails.languages}</a></td>
      <td>${emails.pageSize}</td>
      <td>
        <c:choose>
          <c:when test="${emails.spams}">Enabled</c:when>
          <c:otherwise>Disabled</c:otherwise>
        </c:choose>
      </td>
      <td><pre>${emails.signature}</pre></td>
    </tr>
  </c:forEach>
  </tbody>
</table>

</body>
</html>
