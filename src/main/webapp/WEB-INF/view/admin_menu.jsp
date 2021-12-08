<%--
  Author: Pavel Ravvich.
  Date: 29/10/2017.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>ADMIN</title>
</head>
<body>
<div class="mainMenu"><jsp:include page="_menu.jsp"></jsp:include> <a class="button28" href="<c:url value='/updateUsers' />">Обновить пользователей</a>
    <a class="button28" href="<c:url value='/update_dish_price' />">Обновить стоимость блюд</a>
    <a class="button28" href="<c:url value='/admin_look_orders' />">Просмотр текущих заказов</a>
    <a class="button28" href="<c:url value='/admin_statistic_page' />">Статистика</a></div>
<div class="body">
   Hello admin!
    <a class="button28" href="<c:url value='/create_table' />">Создать таблицу в базе</a></div>

</div>
</body>
</html>
