<%--
  Created by IntelliJ IDEA.
  User: Лидия
  Date: 14.09.2021
  Time: 19:53
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<a href="/">
    MainPage
</a>
||
<a style="color:red" href="<c:url value='/logout' />">Logout</a>

&nbsp;
<span style="color:darkblue">[<c:out value="${sessionScope.role}"/> <c:out value="${sessionScope.login}"/> ]</span>
<hr/>

