<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Setting</title>
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

<h1>Settings</h1>

<form:form action="/email/add" method="post" modelAttribute="email">
    <form:hidden path="id"/>

    <div class="form-group">
        <label for="language">Languages</label>
        <form:select path="languages" items="${languageOptions}" cssClass="form-control" />
    </div>

    <div class="form-group">
        <label for="pageSize">Page Size</label>
        <div>
            Show
            <form:select path="pageSize" items="${pageSizeOptions}" />
            emails per page
        </div>
    </div>

    <div class="form-group">
        <label for="spamsFilter">Spams filter:</label>
        <div>
            <form:checkbox path="spams" />
            <label for="spamsFilter" style="font-weight: normal;">Enable spams filter</label>
        </div>
    </div>

    <div class="form-group">
        <label for="signature">Signature</label>
        <form:textarea path="signature" />

    </div>

    <div class="button-group" style="padding-left: 150px">
        <button type="submit" class="btn-update">Add</button>
        <button type="submit" class="btn-cancel"><a href="/email">Cancel</a></button>
    </div>

</form:form>

</body>
</html>
