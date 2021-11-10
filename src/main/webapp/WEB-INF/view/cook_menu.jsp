<%--

--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>COOK</title>

</head>
<body>
<div class="mainMenu">
    <jsp:include page="_menu.jsp"></jsp:include>
    <a class="button28" href="<c:url value="/dishes_menu_editor"/>">Редактор меню</a>
    <a class="button28" href="<c:url value="/cooking_page"/>">Выполнение заказов</a>
</div>
<div class="body">
   <h3>Hello cook!</h3>
</div>
</body>
</html>
