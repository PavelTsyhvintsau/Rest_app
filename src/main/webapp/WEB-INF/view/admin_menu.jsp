<%--
  Author: Pavel Ravvich.
  Date: 29/10/2017.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>ADMIN</title>
</head>
<body>
<jsp:include page="_menu.jsp"></jsp:include>

<a href="<c:url value='/updateUsers' />">Обновить пользователей</a><br/>
<a href="<c:url value='/update_dish_price' />">Обновить стоимость блюд</a>

</body>
</html>
