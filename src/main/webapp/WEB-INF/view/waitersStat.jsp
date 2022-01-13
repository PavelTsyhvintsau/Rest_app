<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Cooks stat</title>
</head>
<body>

<jsp:include page="_top_menu.jsp"></jsp:include>

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