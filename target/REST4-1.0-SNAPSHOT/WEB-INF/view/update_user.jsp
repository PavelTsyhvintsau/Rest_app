<%--
  Author: Pavel Ravvich.
  Date: 15.10.17.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User update</title>
</head>
<body>
<div class="mainMenu">
    <jsp:include page="_menu.jsp"></jsp:include>
    <a class="button28" href="<c:url value='/updateUsers' />">Обновить пользователей</a>
    <a class="button28" href="<c:url value='/update_dish_price' />">Обновить стоимость блюд</a>
    <a class="button28" href="<c:url value='/admin_look_orders' />">Просмотр текущих заказов</a>
    <a class="button28" href="<c:url value='/admin_statistic_page' />">Статистика</a></div>
</div>
<div class="body">
    <div>Login: <c:out value="${requestScope.user.login}"/> </div>
    <div>Password: <c:out value="${requestScope.user.password}"/> </div>
    <div>Role: <c:out value="${requestScope.user.role}"/> </div>
    <div>ID: <c:out value="${requestScope.user.id}"/> </div>
    <br />

    <form method="post" action="<c:url value='/update_user'/>">
        <p><input type="checkbox" <c:if test="${requestScope.user.active=='true'}"> checked </c:if> name="active" value="true" >isActive</p>
        <label>New login: <input type="text" name="login" value = '${requestScope.user.login}'/></label><br>
        <label>New password: <input type="text" name="password" value = '${requestScope.user.password}'/> </label><br>
        <select name="role">
            <c:forEach var="roleFromlist" items="${requestScope.dao.roleList}">
                <option <c:if test="${roleFromlist eq requestScope.user.role}"> selected = "selected" </c:if> value="${roleFromlist}">${roleFromlist} </option>
            </c:forEach>
        </select>

        <input type="number" hidden name="id" value="${requestScope.user.id}"/>

        <input type="submit" value="Ok" name="Ok"><br>
    </form>
    <a href="<c:url value='/updateUsers' />">Отмена</a>
</div>
</body>
</html>
