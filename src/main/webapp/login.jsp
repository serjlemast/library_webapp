<%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 1/5/23
  Time: 10:43 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Login</title>
</head>
<body>
<div>
  <form action="<%= request.getRequestURL() %>" method="post">
    <h1>User name</h1>
    <input id="loginUserName" name="username">
    <h1>Password</h1>
    <input id="loginPassword" name="password" type="password">
    <button type="submit" id="loginButton">Login</button>
    <br>
    <br>
    <%=  request.getAttribute("error") != null ? request.getAttribute("error") : "" %>
  </form>
</div>
</body>
</html>
