<%--
  Created by IntelliJ IDEA.
  User: Лидия
  Date: 11.10.2021
  Time: 22:30
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Waiter orders menu</title>
    <link href="style/style1.css" rel="stylesheet" type="text/css">
</head>
<body>
<jsp:include page="_menu.jsp"></jsp:include>
<div class="listOrder1">
    <h2> Список моих заказов в очереди ожидания</h2>
    <c:forEach var="order" items="${requestScope.queueOrders}">
        <c:if test="${order.waiter.login == sessionScope.login}" >
            <ul>
                <li>ID заказа: <c:out value="${order.id}"/></li>
                <li>№ стола: <c:out value="${order.tableNumber}"/></li>
                <li>Время оформления заказа: <c:out value="${order.orderCreateTime}"/>.</li>
                <li>Время приготовления: <c:out value="${order.totalCookingTime}"/> мин.</li>
                <li>Состав заказа:
                    <br>
                    <c:forEach var="dish" items="${order.dishes}">
                        <ul>
                            <li><c:out value="${dish.key.dishName}"/> - <c:out value="${dish.value}"/> шт.</li>
                        </ul>
                    </c:forEach></li>
                <li>Стоимость заказа: <c:out value="${order.rublePrice}"/> руб. <c:out value="${order.pennyPrice}"/> коп.</li>
            </ul>
        </c:if>
        <hr/>
    </c:forEach>
</div>
<div class="listOrder2">
    <h2> Список моих заказов готовятся</h2>
    <c:forEach var="order" items="${requestScope.ordersInWork}">
        <c:if test="${order.waiter.login == sessionScope.login}" >
            <ul>
                <li>ID заказа: <c:out value="${order.id}"/></li>
                <li>№ стола: <c:out value="${order.tableNumber}"/></li>
                <li>Время оформления заказа: <c:out value="${order.orderCreateTime}"/>.</li>
                <li>Время приготовления: <c:out value="${order.totalCookingTime}"/> мин.</li>
                <li>Состав заказа:
                    <br>
                    <c:forEach var="dish" items="${order.dishes}">
                        <ul>
                            <li><c:out value="${dish.key.dishName}"/> - <c:out value="${dish.value}"/> шт.</li>
                        </ul>
                    </c:forEach></li>
                <li>Стоимость заказа: <c:out value="${order.rublePrice}"/> руб. <c:out value="${order.pennyPrice}"/> коп.</li>
            </ul>
        </c:if>
        <hr/>
    </c:forEach>
</div>
<div class="listOrder3">
    <h2> Список моих заказов готовы</h2>
    <c:forEach var="order" items="${requestScope.ordersComplete}">
        <c:if test="${order.waiter.login == sessionScope.login}" >
            <ul>
                <li>ID заказа: <c:out value="${order.id}"/></li>
                <li>№ стола: <c:out value="${order.tableNumber}"/></li>
                <li>Время оформления заказа: <c:out value="${order.orderCreateTime}"/>.</li>
                <li>Время приготовления: <c:out value="${order.totalCookingTime}"/> мин.</li>
                <li>Состав заказа:
                    <br>
                    <c:forEach var="dish" items="${order.dishes}">
                        <ul>
                            <li><c:out value="${dish.key.dishName}"/> - <c:out value="${dish.value}"/> шт.</li>
                        </ul>
                    </c:forEach></li>
                <li>Стоимость заказа: <c:out value="${order.rublePrice}"/> руб. <c:out value="${order.pennyPrice}"/> коп.</li>
            </ul>
            <form method="post" action="<c:url value='/order_to_bank'/>">
                <input type="number" hidden name="ID" value="${order.id}" />
                <input type="submit" name="addToOrder" value="Заказ доставлен клиенту"/>
            </form>
        </c:if>
        <hr/>
    </c:forEach>
</div>

</body>
</html>
