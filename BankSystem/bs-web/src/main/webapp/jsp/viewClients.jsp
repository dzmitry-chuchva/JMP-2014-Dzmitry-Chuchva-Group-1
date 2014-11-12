<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title></title>
</head>
<body>
<h1>${bank.name} clients:</h1>
<c:forEach items="${persons}" var="person">
  <p><a href="BankSystem?action=viewAccounts&bankId=${bank.id}&cleintId=${person.id}">${person.firstName} ${person.lastName}</a></p>
</c:forEach>
</body>
</html>
