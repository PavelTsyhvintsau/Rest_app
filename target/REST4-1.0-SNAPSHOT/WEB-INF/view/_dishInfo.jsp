
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="orderShot">
    <c:if test="${dish.isActive() eq 1}" >
        <img src="${dish.dishImagePath}" alt="картинка блюда" height="100">
        <li>Название: <c:out value="${dish.dishName}"/></li>
        <li>Стоимость: <c:out value="${dish.rublePrice}"/> руб. <c:out value="${dish.pennyPrice}"/> коп.</li>
        <form method="post" action="<c:url value='/add_dish_to_order'/>">
            <input type="number" hidden name="id" value="${dish.id}" />
            <input type="number" name="pieces" value="1" />
            <input type="submit" name="addToOrder" value="Добавить в заказ"/>
        </form>
    </c:if>
</div>
