<%--
  Created by IntelliJ IDEA.
  User: Лидия
  Date: 16.09.2021
  Time: 22:24
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Menu for ordering</title>
</head>
<body>
<jsp:include page="_menu.jsp"></jsp:include>
<h3>Добавлено для заказа:</h3><br />
<div>
    <c:forEach var="dish" items="${sessionScope.order.dishes}">
        <ul>
            <li>Название блюда: <c:out value="${dish.key.dishName}"/></li>
            <li>Количество: <c:out value="${dish.value}"/></li>
        </ul>
        <hr/>
    </c:forEach>
    <h4>Стоимость заказа: <c:out value="${sessionScope.order.rublePrice}"/> руб. <c:out value="${sessionScope.order.pennyPrice}"/> коп.</h4>
</div>
<div> <form method="post" action="<c:url value='/add_order_to_queue'/>">
        <input type="submit" name="addToOrder" value="Заказать"/>
</form></div>

<h3>Очередь заказов:</h3><br />
<div>
    <c:forEach var="ord" items="${requestScope.queueOrders}">
        <ul>
            <li>ID заказа: <c:out value="${ord.dishes.toString()}"/></li>
            <li>Количество: <c:out value="${ord.dishes.size()}"/></li>
        </ul>
        <hr/>
    </c:forEach>
    <h4>Стоимость заказа: <c:out value="${sessionScope.order.rublePrice}"/> руб. <c:out value="${sessionScope.order.pennyPrice}"/> коп.</h4>
</div>



<h2>Mеню</h2><br />

<div >
    <h3>Горячие блюда:</h3><br />
    <c:forEach var="dish" items="${requestScope.menuHot}">
         <%@include file="_dishInfo.jsp" %>
    </c:forEach>
</div>
<div >
    <h3>Гарниры:</h3><br />
    <c:forEach var="dish" items="${requestScope.menuGarnish}">
        <c:if test="${dish.isActive() eq 1}" >
                <%@include file="_dishInfo.jsp" %>

        </c:if>

    </c:forEach>
</div>
<div >
    <h3>Салаты:</h3><br />
    <c:forEach var="dish" items="${requestScope.menuSalat}">
        <%@include file="_dishInfo.jsp" %>
    </c:forEach>
</div>
<div >
    <h3>Супы:</h3><br />
    <c:forEach var="dish" items="${requestScope.menuSoup}">
        <%@include file="_dishInfo.jsp" %>
    </c:forEach>
</div>
<div >
    <h3>Горячие напитки:</h3><br />
    <c:forEach var="dish" items="${requestScope.menuHotDrink}">
        <%@include file="_dishInfo.jsp" %>
    </c:forEach>
</div>
<div >
    <h3>Холодные напитки:</h3><br />
    <c:forEach var="dish" items="${requestScope.menuCouldDrink}">
        <%@include file="_dishInfo.jsp" %>
    </c:forEach>
</div>
</body>
</html>
