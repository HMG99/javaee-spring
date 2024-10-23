<%--
  Created by IntelliJ IDEA.
  User: Hovhannes
  Date: 10/11/2024
  Time: 11:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Forgot Password</title>
</head>
<body>
<%
    String errorMessage = (String) request.getAttribute("errorMessage");
    if(errorMessage != null) {
        response.getWriter().print(errorMessage);
    }
%>
<form method="get" action="/set-token">
    <h1>Forgot Password Page</h1>
    Enter your email: <input type="text" name="email"><br><br>
    <input type="submit" value="submit"><br><br>
</form>
</body>
</html>
