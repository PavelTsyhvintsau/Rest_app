<%--
  Created by IntelliJ IDEA.
  User: Лидия
  Date: 13.09.2021
  Time: 0:00
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="_menu.jsp"></jsp:include>
    <div>Название: <c:out value="${requestScope.dish.dishName}"/> </div>
    <div>Время приготовления: <c:out value="${requestScope.dish.dishCookingTime}"/> </div>
    <div>Тип: <c:out value="${requestScope.dish.dishType.toString()}"/> </div>
    <div>Путь к файлу изображения: <c:out value="${requestScope.dish.dishImagePath}"/> </div>
    <div>ID: <c:out value="${requestScope.dish.id}"/> </div>
    <br />
    <form method="post" action="<c:url value='update_dish'/>">

        <label>Новое название: <input type="text" name="name" onblur="if(value=='') value = '${requestScope.dish.dishName}'" onfocus="if(value=='${requestScope.dish.dishName}') value = '${requestScope.dish.dishName}'"/></label><br>
        <label>Новое время готовки: <input type="number" name="cookingTime" onblur="if(value=='') value = '${requestScope.dish.dishCookingTime}'" onfocus="if(value=='${requestScope.dish.dishCookingTime}') value = '${requestScope.dish.dishCookingTime}'"/> </label><br>
        <label>Новый путь к рисунку: <input type="text" name="imagePath" onblur="if(value=='') value = '${requestScope.dish.dishImagePath}'" onfocus="if(value=='${requestScope.dish.dishImagePath}') value = '${requestScope.dish.dishImagePath}'"/> </label><br>
        <select name="type">
            <c:forEach var="typeFromlist" items="${requestScope.menu.dishTypeList}">
                <option value="${typeFromlist}">${typeFromlist}</option>
            </c:forEach>
        </select>

        <input type="number" hidden name="id" value="${requestScope.dish.id}"/>

        <input type="submit" value="Ok" name="Ok"><br>
    </form>
    <a href="<c:url value='/dishes_menu_editor' />">Отмена</a>

</body>
</html>
