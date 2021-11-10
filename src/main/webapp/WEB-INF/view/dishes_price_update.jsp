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
    <c:forEach var="dish" items="${requestScope.menu.dishesList}">
            <div >
            Название блюда: <c:out value="${dish.dishName}"/><br>
            Текущая стоимость: <c:out value="${dish.rublePrice}"/> руб. <c:out value='${dish.pennyPrice}'/> коп.<br>
                <form method="post" action="<c:url value='/update_dish_price'/>">
                    <input type="number" hidden name="id" value="${dish.id}" >
                    <input type="number" name="dishNewPrise" value="${dish.price}">Новая стоимость (в копейках)
                <input type="submit" name="activate" value="Установить"/>
            </form>
            </div>
    </c:forEach>
</div>
</body>
</html>
