<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Access Denied</title>
</head>
<body>
<div class="mainMenu"><jsp:include page="_menu.jsp"></jsp:include></div>
<div class="body">
    <h1 style="color:red">Страница с данным адресом - "<c:out value="${requestScope.path}"/>" не имеется в списке доступных!(необходимо отредактировать Security config)</h1>
</div>
</body>
</html>

