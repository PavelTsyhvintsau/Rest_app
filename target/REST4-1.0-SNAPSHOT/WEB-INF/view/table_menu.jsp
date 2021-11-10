<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Меню столика</title>
    <link href="style/style1.css" rel="stylesheet" type="text/css">
    <script type="text/javascript">
        window.history.forward();
        function prevent()
        {
            window.history.forward();
        }
    </script>
</head>
<body onload="prevent();" onpageshow="if (event.persisted) prevent();" onunload="">
<div class="mainMenu2" style="color: white; text-align: center">
    <h4 style="margin-top: 1px;">Меню для стола № <c:out value="${sessionScope.order.tableNumber}"/> (Официант: <c:out value="${sessionScope.user.login}"/>) Роль: <c:out value="${sessionScope.user.role}"/></h4>
    <a class="button28" style="color: red;position: fixed;right: 0;top: 0;" href="<c:url value='/logout' />">
        Logout
    </a>
</div>
<div class="rightOrderBar" style="text-align: right;">

    <div class="orderShot" style="background-color: cadetblue" >
        <h4>Добавлено для заказа:</h4>
        <c:forEach var="dish" items="${sessionScope.order.dishes}">
            <ul>
                <li>Название блюда: <c:out value="${dish.key.dishName}"/></li>
                <li>Количество: <c:out value="${dish.value}"/></li>
            </ul>
            <hr/>
        </c:forEach>
        <h4>Стоимость заказа: <c:out value="${requestScope.orderTable.rublePrice}"/> руб. <c:out value="${requestScope.orderTable.pennyPrice}"/> коп.</h4>

        <form method="post" action="<c:url value='/add_order_to_TableQueue'/>">
            <input hidden type="number" name="tableNumber" value="${sessionScope.order.tableNumber}"/>
            <input class="button17" type="submit" name="addToOrder" value="Заказать"/>
        </form>
    </div>
    <c:forEach var="order" items="${requestScope.restaurant.ordersBank}">
        <c:if test="${order.tableNumber eq sessionScope.order.tableNumber && order.orderstatus ne 'ISCLOSE'}">
            <div class="orderShot">
                <ul>
                    <c:forEach var="dish" items="${order.dishes}">
                        <li><c:out value="${dish.key.dishName}"/> - <c:out value="${dish.value}"/> шт.</li>
                    </c:forEach>
                </ul>
                Стоимость заказа: <c:out value="${order.rublePrice}"/> руб. <c:out value="${order.pennyPrice}"/> коп.
            </div>
        </c:if>

    </c:forEach>
</div>
<div class="body2">

    <div class="menuContent">

        <h2>Mеню</h2><br />

        <div >
            <h3>Горячие блюда:</h3><br />
            <c:forEach var="dish" items="${requestScope.menuHot}">
                <%@include file="_dishInfoTable.jsp" %>
            </c:forEach>
        </div>
        <div >
            <h3>Гарниры:</h3><br />
            <c:forEach var="dish" items="${requestScope.menuGarnish}">
                <c:if test="${dish.isActive() eq 1}" >
                    <%@include file="_dishInfoTable.jsp" %>

                </c:if>

            </c:forEach>
        </div>
        <div >
            <h3>Салаты:</h3><br />
            <c:forEach var="dish" items="${requestScope.menuSalat}">
                <%@include file="_dishInfoTable.jsp" %>
            </c:forEach>
        </div>
        <div >
            <h3>Супы:</h3><br />
            <c:forEach var="dish" items="${requestScope.menuSoup}">
                <%@include file="_dishInfoTable.jsp" %>
            </c:forEach>
        </div>
        <div >
            <h3>Горячие напитки:</h3><br />
            <c:forEach var="dish" items="${requestScope.menuHotDrink}">
                <%@include file="_dishInfoTable.jsp" %>
            </c:forEach>
        </div>
        <div >
            <h3>Холодные напитки:</h3><br />
            <c:forEach var="dish" items="${requestScope.menuCouldDrink}">
                <%@include file="_dishInfoTable.jsp" %>
            </c:forEach>
        </div>
    </div>
</div>
</body>
</html>