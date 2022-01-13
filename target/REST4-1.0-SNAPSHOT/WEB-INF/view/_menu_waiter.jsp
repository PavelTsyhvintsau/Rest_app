<%--
строка меню для официанта
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head><link href="style/style1.css" rel="stylesheet" type="text/css"></head>

<a href="<c:url value="/menu_for_ordering"/>" class="button28">Меню для заказа</a>
<a href="<c:url value="/waiter_ordering"/>" class="button28">Мои заказы</a>
<a href="<c:url value="/waiter_create_tabletTable"/>"class="button28">Создать планшет для стола</a>
<jsp:include page="_menu.jsp"></jsp:include>