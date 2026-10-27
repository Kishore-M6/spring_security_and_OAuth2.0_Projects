<!DOCTYPE html>
<%@page isELIgnored="false"%>
<html>
<head>
    <title>Home Page for Loan Details</title>
</head>
<body>
<h2>Home</h2>
<%--<jsp:include page="/WEB-INF/jsp/header.jsp"/>--%>
<%@ include file="header.jsp" %>
<a href="${pageContext.request.contextPath}/loan-info">Loan Information</a>
</body>
</html>