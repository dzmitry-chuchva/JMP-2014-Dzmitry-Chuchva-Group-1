<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title></title>
</head>

<body>
  <h1>${accounts[0].personTO.firstName} ${accounts[0].personTO.lastName}'s accounts:</h1>

  <form action="BankSystem?action=exchangeCurrencies" method="POST">
    <c:forEach items="${accounts}" var="account">
      <input type="hidden" name="accountId" value="${account.accountTO.id}" />
      <input type="hidden" name="bankId" value="${account.accountTO.bankId}" />
      <input type="hidden" name="clientId" value="${account.accountTO.personId}" />
      <input type="hidden" name="totalCash" value="${account.accountTO.totalCash}" />

      ${account.accountTO.totalCash}
      <select name="currencyId">
        <c:forEach items="${account.currencyTOs}" var="currency">
          <option value="${currency.id}" ${currency.id == account.accountTO.currencyId ? 'selected' : ''}>${currency.code} - ${currency.fullName}</option>
        </c:forEach>
      </select>
      Exchange rate: <input type="text" name="rate" value="${account.exchangeRate}" />

    </c:forEach>

    <br/>
    <br/>
    <input type="submit" value="Submit" />
  </form>
<br/>
<small><a href="BankSystem?action=viewClients&bankId=${accounts[0].accountTO.bankId}">Back to clients</a></small>
</body>
</html>