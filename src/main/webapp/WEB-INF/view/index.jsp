<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hello page</title>
</head>
<body>
<h1>Hello from Anastasia Akimova!</h1> <br/>
<h2>All users</h2><br/>
<c:forEach var="user" items="${requestScope.users}">
    <ul>
        <li>Name: <c:out value="${user.name}"/></li>
        <li>Age: <c:out value="${user.age}"/></li>
    </ul>
    <hr/>
</c:forEach>

<h2>Create user</h2><br/>
<form method="post" action="">
    <label><input type="text" name="name"></label>Name<br>
    <label><input type="number" name="age"></label>Age<br>
    <input type="submit" value="ok" name="ok"><br>
</form>
</body>
</html>
