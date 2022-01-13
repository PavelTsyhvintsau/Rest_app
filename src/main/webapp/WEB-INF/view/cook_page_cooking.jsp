<%--
  Created by IntelliJ IDEA.
  User: Лидия
  Date: 28.09.2021
  Time: 17:55
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>COOK</title>
    <meta http-equiv="Content-Type" content="text/html; UTF-8">
    <link href="style/style1.css" rel="stylesheet" type="text/css">
    <script src="js/timer.js" type="text/javascript"></script>
</head>
<body onload="init('timer')">

<jsp:include page="_top_menu.jsp"></jsp:include>

<div class="body">
    <h2 class="h2">Выполнение заказов.</h2>
текущий in prop заказ для повара <c:out value="${sessionScope.user.currentOrder}"/>
    <c:if test="${sessionScope.user.currentOrder == -1}" >
        <li>Активность: <c:out value="Нет заказа в работе"/></li>
        <form method="post" action="<c:url value='/activate_cook'/>">
            <input type="number" hidden name="id" value="${sessionScope.user.id}" />
            <input type="submit" name="activate" value="Взять заказ"/>
        </form>
    </c:if>
    <c:if test="${sessionScope.user.currentOrder >0}" >
        <c:set var="order5" scope="request" value="${requestScope.curOrder}"></c:set>
        <div style="border: 1px solid black; padding: 10px;">
            Активность: <c:out value="в работе заказ ${sessionScope.user.currentOrder}. Номер столика ${requestScope.curOrder.tableNumber}."/>

            <div class="orderFull">
                <ul>
                    <li>ID заказа: <c:out value="${order5.id}"/></li>
                    <li>№ стола: <c:out value="${order5.tableNumber}"/>, официант: <c:out value="${order5.creatorID} (${requestScope.restaurant.dao.get().getById(order5.creatorID).login})"/>.</li>
                    <li>Время оформления заказа: <c:out value="${order5.orderCreateTimeString}"/>.</li>
                    <li>Время начала приготовления: <c:out value="${order5.orderStartCookingTimeString}"/>.</li>
                    <li><p> Время прошло с момента оформления (вводные в секундомер "${order5.orderTimeFromCreating}") <span id="timer" style="color: brown; font-size: 150%; font-weight: bold;">${order.orderTimeFromCreating}</span></p></li>
                    <li>Время на приготовление по нормам: <c:out value="${order5.totalCookingTime}"/> мин.</li>
                    <li>Состав заказа:
                        <br>
                        <c:forEach var="dish" items="${order5.dishes}">
                            <ul>
                                <li><c:out value="${dish.key.dishName}"/> - <c:out value="${dish.value}"/> шт.</li>
                                <li>Цена за единицу - <c:out value="${dish.key.rublePrice}"/> руб. <c:out value="${dish.key.pennyPrice}"/> коп.</li>
                            </ul>
                        </c:forEach></li>
                    <li>Стоимость заказа: <c:out value="${order5.rublePrice}"/> руб. <c:out value="${order5.pennyPrice}"/> коп.</li>
                </ul>
            </div>

            <form method="post" action="<c:url value='/order_completed'/>">

                <input type="number" hidden name="id" value="${sessionScope.user.id}" />
                <input type="number" hidden name="orderid" value="${order5.id}" />
                <input type="submit" name="activate" value="выполнен"/>
            </form>
        </div>
    </c:if>
    <br />
    <h4>лист заказов : </h4>
    <c:forEach var="order1" items="${requestScope.restaurant.ordersListFromDd}">
        <c:if test="${order1.orderstatus.toString() eq 'INQUEUE' }" >
        <ul>
            <li>заказ id: <c:out value="${order}"/></li>
            <li>время выполнения: <c:out value="${order1.totalCookingTime}"/></li>
        </ul>
        </c:if>
        <hr/>
    </c:forEach>
</div>
</body>
</html>
