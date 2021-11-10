<%--
  Created by IntelliJ IDEA.
  User: Лидия
  Date: 17.09.2021
  Time: 13:38
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Update price menu</title>
    <style type="text/css">
    </style>
</head>
<body>
<div class="mainMenu">
    <jsp:include page="_menu.jsp"></jsp:include>
    <a class="button28" href="<c:url value='/updateUsers' />">Обновить пользователей</a>
    <a class="button28" href="<c:url value='/update_dish_price' />">Обновить стоимость блюд</a>
    <a class="button28" href="<c:url value='/admin_look_orders' />">Просмотр текущих заказов</a>
    <a class="button28" href="<c:url value='/admin_statistic_page' />">Статистика</a></div>
</div>
<div class="body">
    <c:forEach var="order" items="${requestScope.restaurant.ordersBank}">
        <div class="orderShot">
            Заказ - <c:out value="${order.id}"/><br>
            Состояние - <c:out value="${order.orderstatus.toString()}"/><br>
            Официант - <c:out value="${order.user.login}"/><br>
            Повар - <c:out value="${order.cook.name}"/><br>
            Состав :<br>
            <c:forEach var="dish" items="${order.dishes}">
                <c:out value="${dish.key.dishName}"/> - <c:out value="${dish.value}"/> шт.<br><br>
            </c:forEach>


        </div>
    </c:forEach>
</div>
</body>
</html>
