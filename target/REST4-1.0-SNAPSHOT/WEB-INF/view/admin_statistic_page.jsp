<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>ADMIN</title>
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
    <div class="orderFull">
        Статистика поваров:
        <form method="post" action="<c:url value='/cooks_statistic'/>">

            <label> Выбор поваров:</label>
            <select name="cooks" multiple="multiple">
                <option value="all"selected="selected">Выбрать всех</option>
                <c:forEach var="cook" items="${requestScope.restaurant.dao.get().store}">
                    <c:if test="${cook.role eq 'COOK'}">
                    <option value="${cook.id}">${cook.login}</option>
                    </c:if>
                </c:forEach>
            </select><br>
            <label> Сортировать по:</label>
            <select name="sortBy" >
                <option value="byName"selected="selected">Имени</option>
                <option value="byCount">Количеству заказов</option>
                <option value="byLongTheory">Длительности заказов теоретической</option>
                <option value="byLongPractik">Длительности заказов практической</option>

            </select><br>
            <label> Период для отображения:</label>
            <input type="date" id="start" name="trip-start"
                   value="2018-07-22"
                   min="2018-01-01" max="2022-12-31">
            <input type="date" id="end" name="trip-end"
                   value="2018-07-22"
                   min="2018-01-01" max="2022-12-31"><br>
            <input type="submit" value="Ok" name="Отобразить"><br>
        </form>
    </div>
    <div class="orderFull">
        Статистика официантов:
        <form method="post" action="<c:url value='/waiter_statistic'/>">

            <label> Выбор официантов:</label>
            <select name="usersID" multiple="multiple">
                <option value="all"selected="selected">Выбрать всех</option>
                <c:forEach var="user" items="${requestScope.restaurant.dao.get().store}">
                    <c:if test="${user.role eq 'WAITER'}">
                        <option value="${user.id}">${user.login}</option>
                    </c:if>
                </c:forEach>
            </select><br>
            <label> Сортировать по:</label>
            <select name="sortBy" >
                <option value="byName"selected="selected">Имени</option>
                <option value="byCount">Количеству заказов</option>
                <option value="byCost">Стоимости заказов</option>
            </select><br>
            <label> Период для отображения:</label>
            <input type="date" name="trip-start"
                   value="2018-07-22"
                   min="2018-01-01" max="2022-12-31">
            <input type="date" name="trip-end"
                   value="2018-07-22"
                   min="2018-01-01" max="2022-12-31"><br>
            <input type="submit" value="Ok" name="Отобразить"><br>
        </form>
    </div>

    <div class="orderFull">
        Статистика блюд:
        <form method="post" action="<c:url value='/dish_statistic'/>">

            <label> Выбор блюда:</label>
            <select name="dishes" multiple="multiple">
                <option value="all" selected="selected">Выбрать все</option>
                <c:forEach var="unit" items="${requestScope.restaurant.menu.dishesList}">
                        <option value="${unit.id}">${unit.dishName}</option>
                </c:forEach>
            </select><br>
            <label> Сортировать по:</label>
            <select name="sortBy" >
                <option value="byName"selected="selected">Названию</option>
                <option value="byCount">Количеству заказов</option>
                <option value="byCost">Стоимости заказов</option>
            </select><br>
            <label> Период для отображения:</label>
            <input type="date" name="trip-start"
                   value="2018-07-22"
                   min="2018-01-01" max="2022-12-31">
            <input type="date" name="trip-end"
                   value="2018-07-22"
                   min="2018-01-01" max="2022-12-31"><br>
            <input type="submit" value="Ok" name="Отобразить"><br>
        </form>
    </div>
    <div class="orderFull">
        <a class="button17" href='/order_completed_page' >Просмотр всех заказов в архиве</a>
    </div>
</div>

</body>
</html>