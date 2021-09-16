<%--
  Author: Pavel Ravvich.
  Date: 15.10.17.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User udate</title>
</head>
<body>
<jsp:include page="_menu.jsp"></jsp:include>


<div>Login: <c:out value="${requestScope.user.login}"/> </div>
<div>Password: <c:out value="${requestScope.user.password}"/> </div>
<div>Role: <c:out value="${requestScope.user.role}"/> </div>
<div>ID: <c:out value="${requestScope.user.id}"/> </div>
<br />

<form method="post" action="<c:url value='/update_user'/>">

    <label>New login: <input type="text" name="login" onblur="if(value=='') value = '${requestScope.user.login}'" onfocus="if(value=='${requestScope.user.login}') value = '${requestScope.user.login}'"/></label><br>
    <label>New password: <input type="text" name="password" onblur="if(value=='') value = '${requestScope.user.password}'" onfocus="if(value=='${requestScope.user.password}') value = ''"/> </label><br>
    <select name="role">
        <c:forEach var="roleFromlist" items="${requestScope.dao.roleList}">
            <option value="${roleFromlist}">${roleFromlist}</option>
        </c:forEach>
    </select>

    <input type="number" hidden name="id" value="${requestScope.user.id}"/>

    <input type="submit" value="Ok" name="Ok"><br>
</form>
<a href="<c:url value='/updateUsers' />">Отмена</a>

</body>
</html>
