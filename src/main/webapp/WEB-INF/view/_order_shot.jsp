
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div style="border: 1px solid black;
    padding: 10px;">
    <ul>
        <li>ID заказа: <c:out value="${order.id}"/></li>
        <li>№ стола: <c:out value="${order.tableNumber}"/>, официант: <c:out value="${order.user.login}"/>.</li>
        <li>Повар: <c:out value="${order.cook.name}"/></li>
        <li>Время оформления заказа: <c:out value="${order.orderCreateTime}"/>.</li>
        <li>Время на приготовление по нормам: <c:out value="${order.totalCookingTime}"/> мин.</li>
        <li>Стоимость заказа: <c:out value="${order.rublePrice}"/> руб. <c:out value="${order.pennyPrice}"/> коп.</li>
    </ul>
    <form method="post" action="<c:url value='/order_info'/>">
        <input type="number" hidden name="id" value="${order.id}" />
        <input type="submit" value="Подробная информация"/>
    </form>
</div>
