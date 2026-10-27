<!DOCTYPE html>
<%@page isELIgnored="false"%>
<html>

<head>
    <title>Loan Information and Loan Details</title>
</head>
<body>
<h2>Loan Information and User Details</h2>
<%--<jsp:include page="/WEB-INF/jsp/header.jsp"/>--%>
<%--<%@ include file="header.jsp" %>--%>
<%@ include file="header.jsp" %>
<p>Loan No# :${loanInfo.loanNo} </p>
<p>Loan Holder Name :${loanInfo.loanHolderName} </p>
<p>Loan Type: ${loanInfo.loanType}</p>
<p>Tenure : ${loanInfo.tenure}</p>
<p>Emi Amount: ${loanInfo.emiAmount}</p>

</body>
</html>