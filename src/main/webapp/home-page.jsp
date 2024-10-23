<%@ page import="com.digi.model.User" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="com.digi.model.Address" %><%--
  Created by IntelliJ IDEA.
  User: Hovhannes
  Date: 10/12/2024
  Time: 2:06 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home Page</title>
</head>
<body>
<%
    User user = (User)session.getAttribute("user");
    Address address = (Address)session.getAttribute("address");
    if(user != null) {
        response.getWriter().print(user.getName() + " " + user.getSurname() + " " + (LocalDate.now().getYear() - user.getYear()) + " years old");
    }
    if(address != null) {
        response.getWriter().print(address.getCountry() + " " + address.getCity() + " " + address.getStreet());
    }
%>
<br><br>
<a href="add-address-page.jsp">add address</a>
</body>
</html>
