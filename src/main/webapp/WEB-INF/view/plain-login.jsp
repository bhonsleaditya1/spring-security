<%--
  Created by IntelliJ IDEA.
  User: Aditya
  Date: 23-12-2021
  Time: 19:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Plain Login</title>
  <style>
    .failed{
      color: red;
    }
  </style>
</head>
<body>
<h3>Custom Login Page</h3>
  <form:form action="${pageContext.request.contextPath}/authenticateUser" method="post">
<%--    Check for login error--%>
    <c:if test="${param.error !=null}">
      <i class="failed">Invalid Username and Password</i>
    </c:if>
    <p>
      User name: <input type="text" name="username"/>
    </p>

    <p>
      Password: <input type="password" name="password"/>
    </p>
    <input type="submit" value="Login"/>
  </form:form>

</body>
</html>
