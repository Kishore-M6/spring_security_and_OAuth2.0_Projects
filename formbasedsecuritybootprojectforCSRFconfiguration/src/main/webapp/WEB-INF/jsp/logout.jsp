<!DOCTYPE html>
<%@page isELIgnored="false" %>
<html>
<head>
    <title>Logout Page</title>
</head>
<body>
<h2>Logout</h2>
<p> you are logged out successfully.please login <a href="${pageContext.request.contextPath}/login">here</a>
</p>
<p>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</p>
</body>
</html>