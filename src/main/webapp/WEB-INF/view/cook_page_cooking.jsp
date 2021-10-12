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
<body onload="startTimer()">
<jsp:include page="_menu.jsp"></jsp:include>

<h2 class="h2">Выполнение заказов.</h2>

<c:if test="${requestScope.cook.currentOrder == null}" >
    <li>Активность: <c:out value="нет заказа в работе"/></li>
    <form method="post" action="<c:url value='/activate_cook'/>">
        <input type="number" hidden name="id" value="${requestScope.user.id}" />
        <input type="submit" name="activate" value="взять заказ"/>
    </form>
</c:if>
    <c:if test="${requestScope.cook.currentOrder != null}" >
    <li>Активность: <c:out value="в работе заказ ${requestScope.cook.currentOrder.id}. Номер столика ${requestScope.cook.currentOrder.tableNumber}."/> </li>
        <li><p><span id="timer" style="color: brown; font-size: 150%; font-weight: bold;">${requestScope.cook.currentOrder.cookingTimeHours}:${requestScope.cook.currentOrder.cookingTimeMinutes}:00</span></p></li>
        <div>

            <c:forEach var="order" items="${sessionScope.user.cook.currentOrder.dishes}">
                <ul>
                    <li>Название блюда: <c:out value="${order.key.dishName}"/></li>
                    <li>Количество: <c:out value="${order.value}"/></li>
                </ul>
                <hr/>
            </c:forEach>
            <h4>Время выполнения :<c:out value="${sessionScope.user.cook.currentOrder.totalCookingTime}" /> секунд</h4>
        </div>
    <form method="post" action="<c:url value='/order_completed'/>">

        <input type="number" hidden name="id" value="${requestScope.user.id}" />
        <input type="submit" name="activate" value="выполнен"/>
    </form>
    </c:if>
<br />

<h4>лист заказов :<c:out value="${sessionScope.user.cook.queue}" /> </h4>
<c:forEach var="order" items="${sessionScope.user.cook.queue}">
    <ul>
        <li>заказ id: <c:out value="${order}"/></li>
        <li>время выполнения: <c:out value="${order.totalCookingTime}"/></li>
    </ul>
    <hr/>
</c:forEach>

</body>
</html>
