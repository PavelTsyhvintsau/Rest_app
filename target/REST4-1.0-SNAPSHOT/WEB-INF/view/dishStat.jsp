<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Cooks stat</title>
</head>
<body>

<jsp:include page="_top_menu.jsp"></jsp:include>

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