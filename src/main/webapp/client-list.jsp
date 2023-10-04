<%--
  Created by IntelliJ IDEA.
  User: karim
  Date: 10/2/23
  Time: 20:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Client List</title>
</head>
<body>
<h1>Client List</h1>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Email</th>
        <th>Actions</th>
    </tr>
    <c:forEach items="${clients}" var="client">
        <tr>
            <td>${client.id_client}</td>
            <td>${client.nom} ${client.prenom}</td>
            <td>${client.email}</td>
            <td>
                <a href="clients?action=edit&id=${client.id_client}">Edit</a> |
                <a href="clients?action=delete&id=${client.id_client}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
<a href="clients?action=create">Create New Client</a>
</body>
</html>

