<%--
  Created by IntelliJ IDEA.
  User: Лидия
  Date: 16.09.2021
  Time: 21:08
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Waiter menu</title>
</head>
<body>
<jsp:include page="_menu.jsp"></jsp:include>

<a href="<c:url value="/menu_for_ordering"/>">Menu for ordering</a>
</body>
</html>
