<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
</head>
<body>
  <h1>Available banks:</h1>
  <c:forEach items="${banks}" var="bank">
      <p><a href="BankSystem?action=viewClients&bankId=${bank.id}">${bank.name}</a></p>
  </c:forEach>
</body>
</html>
