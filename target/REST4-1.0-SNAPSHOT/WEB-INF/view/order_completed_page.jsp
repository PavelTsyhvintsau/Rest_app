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

    <table class="table">
        <tr>
            <td>ID заказа</td>
            <td>Состояние</td>
            <td>Официант</td>
            <td>Повар</td>
            <td>Время заказа</td>
            <td>Время выполнения</td>
            <td>Состав</td>
            <td>Цена</td>
        </tr>
        <c:forEach var="order" items="${requestScope.restaurant.ordersListFromDd}">
            <tr>
                <td><c:out value="${order.id}"/></td>
                <td><c:out value="${order.orderstatus}"/></td>
                <td><c:out value="ID=${order.creatorID}(${requestScope.restaurant.dao.get().getById(order.creatorID).login})"/></td>
                <td><c:out value="ID=${order.cookID}(${requestScope.restaurant.dao.get().getById(order.cookID).login})"/></td>
                <td><c:out value="${order.orderCreateTimeString}"/></td>
                <td><c:out value="${order.orderToClientTimeString}"/></td>
                <td><c:forEach var="dish" items="${order.dishes}">
                    <c:out value="${dish.key.dishName}"/> - <c:out value="${dish.value}"/> шт.<br>
                    </c:forEach>
                </td>
                <td><c:out value="${order.rublePrice}р. ${order.pennyPrice}коп."/></td>
            </tr>
        </c:forEach>


    </table>
</div>
</body>
</html>
