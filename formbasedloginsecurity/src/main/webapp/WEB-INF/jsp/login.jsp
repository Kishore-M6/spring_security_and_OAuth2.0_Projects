<!DOCTYPE html>
<%@page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Login Page</title>
</head>
<body>
<h2>Login here</h2>
<p style="color: red;">
    <c:if test="${param.error eq ''}">
        Invalid Credentials
    </c:if>

</p>
<form action="${pageContext.request.contextPath}/j_login" method="post">
    <p>
      Username:  <input type="text" name="j_username" placeholder="Username" />
    </p>
    <p>
      Password:  <input type="password" name="j_password" placeholder="Password" />
    </p>
    <button type="submit">Login</button>
</form>
</body>
</html>