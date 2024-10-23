<%--
  Created by IntelliJ IDEA.
  User: Hovhannes
  Date: 10/11/2024
  Time: 9:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<%
    String errorMessage = (String) request.getAttribute("errorMessage");
    if(errorMessage != null) {
        response.getWriter().print(errorMessage);
    }
%>
<form method="post" action="/registration">
    <h1>Registration Page</h1>
    name: <input type="text" name="name"><br><br>
    surname: <input type="text" name="surname"><br><br>
    year: <input type="text" name="year"><br><br>
    email: <input type="text" name="email"><br><br>
    password: <input type="text" name="password"><br><br>
    <input type="submit" value="sign-up"><br><br>
</form>
</body>
</html>
