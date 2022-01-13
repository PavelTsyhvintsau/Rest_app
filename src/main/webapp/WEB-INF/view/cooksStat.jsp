<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Cooks stat</title>
</head>
<body>

<jsp:include page="_top_menu.jsp"></jsp:include>

<div class="body">
    Статистика поваров с ${requestScope.start} по ${requestScope.end}<br>

    <table class="table">
        <tr>
            <td>Повар</td>
            <td>ID</td>
            <td>Заказов выполнил</td>
            <td>Мин. в теории</td>
            <td>Мин. затратил фактически</td>
        </tr>
        <c:forEach var="val1" items="${requestScope.infoCooksList}">
            <tr>
                <td><c:out value="${val1.name}"/></td>
                <td><c:out value="${val1.id}"/></td>
                <td><c:out value="${val1.countOrders}"/></td>
                <td><c:out value="${val1.ordersLongTheory}"/></td>
                <td><c:out value="${val1.ordersLongPraсtic/60000}"/></td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>