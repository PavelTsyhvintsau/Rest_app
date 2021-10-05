<%--

--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>COOK</title>

</head>
<body>
<jsp:include page="_menu.jsp"></jsp:include>


<a href="<c:url value="/dishes_menu_editor"/>">Редактор меню</a><br/>
<a href="<c:url value="/cooking_page"/>">Выполнение заказов</a>

</body>
</html>
