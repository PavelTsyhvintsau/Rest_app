<%--
строка меню для планшета
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head><link href="style/style1.css" rel="stylesheet" type="text/css"></head>

<h4 style="margin-top: 1px;">Меню для стола № <c:out value="${sessionScope.order.tableNumber}"/> (Официант: <c:out value="${sessionScope.user.login}"/>) Роль: <c:out value="${sessionScope.user.role}"/></h4>
<a class="button28" style="color: red;position: fixed;right: 0;top: 0;" href="<c:url value='/logout' />">
    Logout
</a>