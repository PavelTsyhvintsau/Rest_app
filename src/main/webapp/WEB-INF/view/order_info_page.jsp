<%--
  Created by IntelliJ IDEA.
  User: Лидия
  Date: 25.10.2021
  Time: 20:02
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Order Info</title>
    <script src="js/timer.js" type="text/javascript"></script>
</head>
<body onload="init('timer')">

<jsp:include page="_top_menu.jsp"></jsp:include>

<div class="body">
   <c:set var="order" scope="page" value="${requestScope.order}"/>
    <div>
        <ul>
            <li>ID заказа: <c:out value="${order.id}"/></li>
            <li>№ стола: <c:out value="${order.tableNumber}"/>, официант: <c:out value="${order.creatorID}(${requestScope.restaurant.dao.get().getById(order.creatorID).login})"/>.</li>
            <li>Время оформления заказа: <c:out value="${order.orderCreateTimeString}"/>.</li>
            <li>Время начала приготовления: <c:out value="${order.orderStartCookingTimeString}"/>.</li>
            <li><p> Время прошло с момента оформления (вводные в секундомер "${order.orderTimeFromCreating}") <span id="timer" style="color: brown; font-size: 150%; font-weight: bold;">${order.orderTimeFromCreating}</span></p></li>
            <li>Время окончания приготовления: <c:out value="${order.orderEndCookingTime}"/>.</li>
            <li>Время потрачено на приготовление: <c:out value="${order.timeFactCookingString}"/> мин.</li>
            <li>Время на приготовление по нормам: <c:out value="${order.totalCookingTime}"/> мин.</li>
            <li>Время доставки клиенту: <c:out value="${order.orderToClientTimeString}"/>.</li>
            <li>Состав заказа:
                <br>
                <c:forEach var="dish" items="${order.dishes}">
                    <ul>
                        <li><c:out value="${dish.key.dishName}"/> - <c:out value="${dish.value}"/> шт.</li>
                        <li>Цена за единицу - <c:out value="${dish.key.rublePrice}"/> руб. <c:out value="${dish.key.pennyPrice}"/> коп.</li>
                    </ul>
                </c:forEach></li>
            <li>Стоимость заказа: <c:out value="${order.rublePrice}"/> руб. <c:out value="${order.pennyPrice}"/> коп.</li>
        </ul>
    </div>
</div>
</body>
</html>
