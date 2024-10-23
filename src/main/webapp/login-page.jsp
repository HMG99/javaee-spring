<%--
  Created by IntelliJ IDEA.
  User: Hovhannes
  Date: 10/11/2024
  Time: 9:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<%
    String errorMessage = (String) request.getAttribute("errorMessage");
    if(errorMessage != null) {
        response.getWriter().print(errorMessage);
    }
%>
<form method="post" action="/login">
    <h1>Login Page</h1>
    username: <input type="text" name="username"><br><br>
    password: <input type="text" name="password"><br><br>
    <input type="submit" value="sign-in"><br><br>
</form>
<a href="registration-page.jsp">Registration</a><br><br>
<a href="set-reset-token.jsp">Forgot Password</a><br><br>
</body>
</html>
