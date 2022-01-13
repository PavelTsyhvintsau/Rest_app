<%--
строка меню для админа
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head><link href="style/style1.css" rel="stylesheet" type="text/css"></head>
<a class="button28" href="<c:url value='/updateUsers' />">Обновить пользователей</a>
<a class="button28" href="<c:url value='/update_dish_price' />">Обновить стоимость блюд</a>
<a class="button28" href="<c:url value='/admin_look_orders' />">Просмотр текущих заказов</a>
<a class="button28" href="<c:url value='/admin_statistic_page' />">Статистика</a>
<jsp:include page="_menu.jsp"></jsp:include>