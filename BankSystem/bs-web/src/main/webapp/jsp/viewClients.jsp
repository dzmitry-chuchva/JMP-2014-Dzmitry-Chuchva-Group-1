<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title></title>
</head>
<body>
<h1>${bank.name} clients:</h1>
<c:forEach items="${persons}" var="person">
  <a href="BankSystem?action=manageAccounts&bankId=${bank.id}&clientId=${person.id}">${person.firstName} ${person.lastName}</a><br/>
</c:forEach>
<br/>
<br/>
<small><a href="BankSystem?action=viewBanks">Back to banks</a></small>
</body>
</html>
