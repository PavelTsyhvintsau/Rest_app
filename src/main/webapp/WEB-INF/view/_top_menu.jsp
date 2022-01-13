<%--
 меню общее для всех
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="mainMenu">
    <c:if test="${sessionScope.user.role.toString() eq 'COOK'}">
        <jsp:include page="_menu_cook.jsp"></jsp:include>
    </c:if>
    <c:if test="${sessionScope.user.role.toString() eq 'ADMIN'}">
        <jsp:include page="_menu_admin.jsp"></jsp:include>
    </c:if>
    <c:if test="${sessionScope.user.role.toString() eq 'WAITER'}">
        <jsp:include page="_menu_waiter.jsp"></jsp:include>
    </c:if>
    <c:if test="${sessionScope.user.role.toString() eq 'MANAGER'}">
        <jsp:include page="_menu_manager.jsp"></jsp:include>
    </c:if>
    <c:if test="${sessionScope.user.role.toString() eq 'TABLET_TABLE'}">
        <jsp:include page="_menu_tablet_table.jsp"></jsp:include>
    </c:if>

</div>