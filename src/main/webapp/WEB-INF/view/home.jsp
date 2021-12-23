<%--
  Created by IntelliJ IDEA.
  User: Aditya
  Date: 23-12-2021
  Time: 18:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

        <html>
<head>
    <title>Home Page</title>
</head>
<body>
    <h2>Home Page</h2>
    <p>Welcome to Home Page!!!</p>
    <form:form action="${pageContext.request.contextPath}/logout" method="post">
        <input type="submit" value="Logout"/>
    </form:form>
    <hr>
    User: <security:authentication property="principal.username"/>
    <br>
    <br>
    Role(s): <security:authentication property="principal.authorities"/>
    <hr>


</body>
</html>
