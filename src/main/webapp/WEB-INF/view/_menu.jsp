<%--
  Created by IntelliJ IDEA.
  User: Лидия
  Date: 14.09.2021
  Time: 19:53
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head><link href="style/style1.css" rel="stylesheet" type="text/css"></head>
<span class="button28" style="color: white">[ Роль:<c:out value="${sessionScope.role}"/>/ Логин:<c:out value="${sessionScope.login}"/> ]</span>
<a class="button28" style="color: red" href="<c:url value='/logout' />">
    Logout
</a>
<a class="button28" href="/">
    MainPage
</a>





