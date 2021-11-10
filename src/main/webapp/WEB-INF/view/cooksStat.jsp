<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Cooks stat</title>
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
    cooksStat.jsp page<br>


    <c:forEach var="val" items="${requestScope.infoCooksList}">
        <div class="orderShot">
            <c:out value="${val.name}"/> <br>
            <c:out value="${val.countOrders}"/> - заказов выполнил,<br>
            <c:out value="${val.ordersLongTheory}"/>минут в теории, <c:out value="${val.ordersLongPraсtic/60000}"/> минут затратил по факту.<br>
        </div>
    </c:forEach>

</div>
</body>
</html>