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
<jsp:include page="_menu.jsp"></jsp:include>

<h3>Тукущая роль: <c:out value="${sessionScope.role}"/> <br/>
    Логин: <c:out value="${sessionScope.login}"/>! <a href="<c:url value='/logout' />">Logout</a></h3>
<hr/>
<a href="<c:url value='/updateUsers' />">Update users</a>
<br/>


</body>
</html>
