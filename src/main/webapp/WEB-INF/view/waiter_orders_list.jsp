<%--
  Created by IntelliJ IDEA.
  User: Лидия
  Date: 11.10.2021
  Time: 22:30
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Waiter orders menu</title>
    <meta http-equiv="Content-Type" content="text/html; UTF-8">
    <link href="style/style1.css" rel="stylesheet" type="text/css">
    </head>
<body >
<div class="mainMenu">
    <jsp:include page="_menu.jsp"></jsp:include>
    <a href="<c:url value="/menu_for_ordering"/>" class="button28">Меню для заказа</a>
    <a href="<c:url value="/waiter_ordering"/>" class="button28">Мои заказы</a>
    <a href="<c:url value="/waiter_create_tabletTable"/>"class="button28">Создать планшет для стола</a>
</div>
<div class="body" style="height: 100%">
    <div class="listOrder1">
        <h2> Список моих заказов в очереди ожидания</h2>
        <c:forEach var="order" items="${requestScope.ordersBank}">
            <c:if test="${order.creatorID == sessionScope.user.id && order.orderstatus eq 'INQUEUE' }" >
                <%@include file="_order_shot.jsp" %>
            </c:if>
        </c:forEach>
    </div>
    <div class="listOrder2">
        <h2> Список моих заказов готовятся</h2>
        <c:forEach var="order" items="${requestScope.ordersBank}">
            <c:if test="${order.creatorID == sessionScope.user.id && order.orderstatus eq 'INWORK'}" >
                <%@include file="_order_shot.jsp" %>

            </c:if>
        </c:forEach>
    </div>
    <div class="listOrder3">
        <h2> Список моих заказов готовых для доставки:</h2>
        <c:forEach var="order" items="${requestScope.ordersBank}">
            <c:if test="${order.creatorID == sessionScope.user.id && order.orderstatus eq 'ISREADY'}" >
                <div style="border: 2px solid black; padding: 5px;">
                    <%@include file="_order_shot.jsp" %>
                    <form method="post" action="<c:url value='/order_to_bank'/>">
                        <input type="number" hidden name="ID" value="${order.id}" />
                        <input type="submit" name="addToOrder" value="Заказ доставлен клиенту"/>
                    </form>
                </div>
            </c:if>
        </c:forEach>
    </div>
</div>
</body>
</html>
