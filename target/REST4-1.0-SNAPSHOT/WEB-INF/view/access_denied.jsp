<%--
  Created by IntelliJ IDEA.
  User: Лидия
  Date: 14.09.2021
  Time: 16:52
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Access Denied</title>
</head>
<body>
<jsp:include page="_menu.jsp"></jsp:include>
Access Denied
<hr/>

<a href="<c:url value='/logout' />">Logout</a>
<hr/>
</body>
</html>
