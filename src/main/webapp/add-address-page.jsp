<%--
  Created by IntelliJ IDEA.
  User: Hovhannes
  Date: 10/18/2024
  Time: 2:13 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Address</title>
</head>
<body>
<form method="post" action="/add-address">
    <h1>Address Page</h1>
    country: <input type="text" name="country"><br><br>
    city: <input type="text" name="city"><br><br>
    street: <input type="text" name="street"><br><br>
    <input type="submit" value="Submit"><br><br>
</form>
</body>
</html>
