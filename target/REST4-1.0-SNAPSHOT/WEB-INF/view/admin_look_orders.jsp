<%--
  Created by IntelliJ IDEA.
  User: Лидия
  Date: 12.10.2021
  Time: 23:07
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Admin orders menu</title>
    <link href="style/style1.css" rel="stylesheet" type="text/css">
</head>
<body>

<jsp:include page="_top_menu.jsp"></jsp:include>

<div class="body">
    <div class="listOrder1">
        <h2> Список заказов в очереди:</h2>
        <c:forEach var="order" items="${requestScope.restaurant.ordersListFromDd}">
            <c:if test="${order.orderstatus eq 'INQUEUE'}" >
            <%@include file="_order_shot.jsp" %>
            </c:if>
        </c:forEach>
    </div>
    <div class="listOrder2">
        <h2> Список заказов готовятся:</h2>
        <c:forEach var="order" items="${requestScope.restaurant.ordersListFromDd}">
            <c:if test="${order.orderstatus eq 'INWORK'}" >
                <%@include file="_order_shot.jsp" %>
            </c:if>
        </c:forEach>
    </div>
    <div class="listOrder3">
        <h2> Список заказов готовы:</h2>
        <c:forEach var="order" items="${requestScope.restaurant.ordersListFromDd}">
            <c:if test="${order.orderstatus eq 'ISREADY'}" >
                <%@include file="_order_shot.jsp" %>
            </c:if>

        </c:forEach>
    </div>
</div>
<div>

</div>
</body>
</html>
