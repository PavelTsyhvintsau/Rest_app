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
    <h3>Статистика заказа блюд с <c:out value="${sessionScope.start}"/> по <c:out value="${sessionScope.end}"/>:</h3><br>
    <table class="table">
        <tr>
            <td>Блюдо</td>
            <td>ID</td>
            <td>Заказов</td>
            <td>Цена ед.</td>
            <td>Общая стоимость.</td>
        </tr>
        <c:forEach var="val1" items="${requestScope.infoDishList}">
            <tr>
                <td><c:out value="${val1.name}"/></td>
                <td><c:out value="${val1.dish.id}"/></td>
                <td><c:out value="${val1.count}"/></td>
                <td><c:out value="${val1.dish.rublePrice}р. ${val1.dish.pennyPrice}коп."/></td>
                <td><c:out value="${val1.rubCost}р. ${val1.pennyCost}коп."/></td>
            </tr>
        </c:forEach>
    </table>




    <c:forEach var="val" items="${requestScope.infoDishList}">
        <div class="orderShot">
            <c:out value="${val.name}"/> <br>
            <c:out value="${val.count}"/> - шт. заказано,<br>
            <c:out value="${val.cost}"/> - общая стоимость(коп.). <br>
        </div>
    </c:forEach>
</div>
</body>
</html>