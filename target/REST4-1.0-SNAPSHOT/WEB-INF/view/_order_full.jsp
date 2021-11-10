<%--

--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="orderFull">
    <ul>
        <li>ID заказа: <c:out value="${order.id}"/></li>
        <li>№ стола: <c:out value="${order.tableNumber}"/>, официант: <c:out value="${order.user.login}"/>.</li>
        <li>Время оформления заказа: <c:out value="${order.orderCreateTime}"/>.</li>
        <li>Время начала приготовления: <c:out value="${order.orderStartCookingTime}"/>.</li>
        <li><p> Время прошло с момента оформления (вводные в секундомер "${order.orderTimeFromCreating}") <span id="timer" style="color: brown; font-size: 150%; font-weight: bold;">${order.orderTimeFromCreating}</span></p></li>
        <li>Время окончания приготовления: <c:out value="${order.orderEndCookingTime}"/>.</li>
        <li>Время потрачено на приготовление: <c:out value="${order.timeFactCooking}"/> мин.</li>
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
