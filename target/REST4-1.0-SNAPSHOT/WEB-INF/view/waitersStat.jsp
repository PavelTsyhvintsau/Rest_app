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
    Статистика официантов с ${requestScope.start} по ${requestScope.end}<br>
    <table class="table">
        <tr>
            <td>Официант</td>
            <td>ID</td>
            <td>Заказов выполнил</td>
            <td>Мин. на доставку ушло</td>
            <td>Общая стоимость</td>
        </tr>
        <c:forEach var="val1" items="${requestScope.infoWaitersList}">
            <tr>
                <td><c:out value="${val1.name}"/></td>
                <td><c:out value="${val1.id}"/></td>
                <td><c:out value="${val1.countOrders}"/></td>
                <td><c:out value="${val1.ordersLongPraсtic}"/> мин.</td>
                <td><c:out value="${val1.rublesPrice}р. ${val1.pennyPrice}коп."/></td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>