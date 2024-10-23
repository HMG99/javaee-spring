<%--
  Created by IntelliJ IDEA.
  User: Hovhannes
  Date: 10/11/2024
  Time: 11:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Verification</title>
</head>
<body>
<%
    String errorMessage = (String) request.getAttribute("errorMessage");
    if(errorMessage != null) {
        response.getWriter().print(errorMessage);
    }
%>
<form action="/verify" method="post">
    <h1>Verification Page</h1>
    Verification Code: <input type="text" name="verificationCode"><br><br>
    <input type="submit" value="send"><br><br>
</form>
</body>
</html>
