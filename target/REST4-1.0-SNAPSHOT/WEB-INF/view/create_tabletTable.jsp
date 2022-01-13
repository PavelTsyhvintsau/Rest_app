<%--

--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Создание меню столика</title>
</head>
<body>

<jsp:include page="_top_menu.jsp"></jsp:include>

<div class="body">
    Создание планшета для посетителей.
    <form method="post" action="<c:url value='/waiter_create_tabletTable'/>">
        <label> <input type="number" name="tableNumber" required></label>№ стола<br>
        <input type="submit" name="addTabletTable" value="Создать"/>
    </form>
</div>
</body>
</html>
