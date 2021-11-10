<%--

--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Waiter menu</title>
    <link href="style/style1.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="mainMenu">
    <jsp:include page="_menu.jsp"></jsp:include>
    <a href="<c:url value="/menu_for_ordering"/>" class="button28">Меню для заказа</a>
    <a href="<c:url value="/waiter_ordering"/>" class="button28">Мои заказы</a>
    <a href="<c:url value="/waiter_create_tabletTable"/>"class="button28">Создать планшет для стола</a>
</div>
<div class="body">

</div>
</body>
</html>
