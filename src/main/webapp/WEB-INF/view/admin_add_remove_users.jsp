<%--
  Created by IntelliJ IDEA.
  User: Лидия
  Date: 08.09.2021
  Time: 10:30
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users redactor</title>
</head>
<body>
<jsp:include page="_menu.jsp"></jsp:include>
<h3>Тукущая роль: <c:out value="${sessionScope.role}"/> <br/>
    Логин: <c:out value="${sessionScope.login}"/>! <a href="<c:url value='/logout' />">Logout</a></h3>
<hr/>
<h4>Users redactor</h4>
<hr/>
<h3>Все пользователи</h3><br />

-<c:forEach var="dao" items="${requestScope.dao.store}">
    <ul>

    <li>Login: <c:out value="${dao.login}"/></li>
    <li>Password: <c:out value="${dao.password}"/></li>
     <li>Role: <c:out value="${dao.role}"/></li>
        <li>ID: <c:out value="${dao.id}"/></li>

    <form method="post" action="<c:url value='/delete_user'/>">
    <input type="number" hidden name="id" value="${dao.id}" />
    <input type="submit" name="delete" value="Удалить"/>
    </form>

    <form method="get" action="<c:url value='/update_user'/>">
    <input type="number" hidden name="id" value="${dao.id}" />
    <input type="submit" value="Редактированть"/>
    </form>
    </ul>
    <hr />
</c:forEach>

<h3>Создание нового пользователя</h3><br />

<form method="post" action="<c:url value='/add_user'/>">

    <label><input type="text" name="login"></label>Логин<br>

    <label><input type="text" name="password"></label>Пароль<br>

    <select name="role">
        <c:forEach var="role" items="${requestScope.dao.roleList}">
            <option value="${role}">${role}</option>
        </c:forEach>
    </select>

    <input type="submit" value="Ok" name="Ok"><br>
</form>

<hr/>


<hr/>

</body>
</html>
