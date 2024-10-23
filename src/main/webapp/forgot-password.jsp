<%--
  Created by IntelliJ IDEA.
  User: Hovhannes
  Date: 10/12/2024
  Time: 12:02 AM
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
<form method="post" action="/set-token">
    <h1>Forgot Password Page</h1>
    Reset Token: <input type="text" name="resetToken"><br><br>
    password: <input type="text" name="password"><br><br>
    confirm password: <input type="text" name="confirmPassword"><br><br>
    <input type="submit" value="Submit"><br><br>
</form>
</body>
</html>
