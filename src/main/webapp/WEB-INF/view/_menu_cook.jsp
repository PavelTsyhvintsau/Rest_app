<%--
строка меню для повара
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head><link href="style/style1.css" rel="stylesheet" type="text/css"></head>

<a class="button28" href="<c:url value="/dishes_menu_editor"/>">Редактор меню</a>
<a class="button28" href="<c:url value="/cooking_page"/>">Выполнение заказов</a>
<jsp:include page="_menu.jsp"></jsp:include>



