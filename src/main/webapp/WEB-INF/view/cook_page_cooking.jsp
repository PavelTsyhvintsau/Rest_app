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
<div class="mainMenu">
    <jsp:include page="_menu.jsp"></jsp:include>
    <a class="button28" href="<c:url value="/dishes_menu_editor"/>">Редактор меню</a>
    <a class="button28" href="<c:url value="/cooking_page"/>">Выполнение заказов</a>
</div>
<div class="body">
    <h2 class="h2">Выполнение заказов.</h2>

    <c:if test="${sessionScope.user.currentOrder == -1}" >
        <li>Активность: <c:out value="Нет заказа в работе"/></li>
        <form method="post" action="<c:url value='/activate_cook'/>">
            <input type="number" hidden name="id" value="${sessionScope.user.id}" />
            <input type="submit" name="activate" value="Взять заказ"/>
        </form>
    </c:if>
        <c:if test="${sessionScope.user.currentOrder >0}" >
            <c:set var="order" scope="request" value="${requestScope.restaurant.getOrderFromDdBiID(sessionScope.user.currentOrder)}"></c:set>
            <div style="border: 1px solid black; padding: 10px;">
                Активность: <c:out value="в работе заказ ${sessionScope.user.currentOrder}. Номер столика ${requestScope.restaurant.getOrderFromDdBiID(sessionScope.user.currentOrder).tableNumber}."/>

                    <%@include file="_order_full.jsp" %>


                <form method="post" action="<c:url value='/order_completed'/>">

                    <input type="number" hidden name="id" value="${sessionScope.user.id}" />
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
