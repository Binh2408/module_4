<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Edit Setting</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 40px;
        }

        h1 {
            margin-bottom: 30px;
        }

        .form-group {
            display: flex;
            align-items: center;
            margin-bottom: 20px;
        }

        .form-group label {
            width: 150px;
            font-weight: bold;
        }

        .form-group select,
        .form-group textarea {
            flex: 1;
            padding: 5px;
        }

        textarea {
            height: 100px;
            resize: vertical;
        }

        .checkbox-group {
            display: flex;
            align-items: center;
            margin-bottom: 20px;
        }

        .checkbox-group label {
            margin-left: 5px;
            font-weight: normal;
        }

        .button-group {
            margin-top: 30px;
        }

        button {
            padding: 6px 20px;
            margin-right: 10px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        .btn-update {
            background-color: #2196F3;
            color: white;
        }

        .btn-cancel {
            background-color: white;
            border: 1px solid #ccc;
        }
    </style>

</head>
<body>

<h1>Edit Email Setting</h1>

<form:form action="/email/update" method="post" modelAttribute="email">
    <form:hidden path="id"/>

    <div class="form-group">
        <label>Language:</label>
        <form:select path="languages" items="${languageOptions}"/>
    </div>

    <div class="form-group">
        <label>Page Size:</label>
        <form:select path="pageSize" items="${pageSizeOptions}" />
    </div>

    <div class="form-group">
        <label>Spam Filter:</label>
        <form:checkbox path="spams" /> Enable spam filter
    </div>

    <div class="form-group">
        <label>Signature:</label>
        <form:textarea path="signature"/>
    </div>

    <div class="form-group">
        <button type="submit">Update</button>
        <a href="/email">Cancel</a>
    </div>
</form:form>

</body>
</html>
