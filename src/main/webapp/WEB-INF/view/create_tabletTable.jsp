<%--

--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Создание меню столика</title>
</head>
<body>
<div class="mainMenu">
    <jsp:include page="_menu.jsp"></jsp:include>
    <a href="<c:url value="/menu_for_ordering"/>" class="button28">Меню для заказа</a>
    <a href="<c:url value="/waiter_ordering"/>" class="button28">Мои заказы</a>
    <a href="<c:url value="/waiter_create_tabletTable"/>"class="button28">Создать планшет для стола</a>
</div>
<div class="body">
    Создание планшета для посетителей.
    <form method="post" action="<c:url value='/waiter_create_tabletTable'/>">
        <label> <input type="number" name="tableNumber" required></label>№ стола<br>
        <input type="submit" name="addTabletTable" value="Создать"/>
    </form>
</div>
</body>
</html>
