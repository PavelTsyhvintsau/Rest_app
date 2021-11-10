
<%--
  Created by IntelliJ IDEA.
  User: Лидия
  Date: 10.09.2021
  Time: 13:48
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Menu editor</title>

</head>
<body>
<div class="mainMenu">
    <jsp:include page="_menu.jsp"></jsp:include>
    <a class="button28" href="<c:url value="/dishes_menu_editor"/>">Редактор меню</a>
    <a class="button28" href="<c:url value="/cooking_page"/>">Выполнение заказов</a>
</div>
<div class="body">
    <h3>Все блюда меню</h3><br />
    <c:forEach var="dish" items="${requestScope.menu.dishesList}">
        <ul>

            <li>Название блюда: <c:out value="${dish.dishName}"/></li>
            <li>Тип: <c:out value="${dish.dishType}"/></li>
            <li>Время приготовления: <c:out value="${dish.dishCookingTime}"/></li>
            <li>Путь файлу к картинки: <c:out value="${dish.dishImagePath}"/></li>
            <li>ID: <c:out value="${dish.id}"/></li>
            <img src="${dish.dishImagePath}" alt="картинка блюда" height="100">
        <c:if test="${dish.isActive() eq 0}" >
            <li>Активно: <c:out value="нет"/></li>
            <form method="post" action="<c:url value='/activate_dish'/>">
                <input type="number" hidden name="id" value="${dish.id}" />
                <input type="number" hidden name="act" value="1" />
                <input type="submit" name="activate" value="Активировать"/>
            </form>
        </c:if>
            <c:if test="${dish.isActive() eq 1}" >
                <li>Активно: <c:out value="да"/></li>
                <form method="post" action="<c:url value='/activate_dish'/>">
                    <input type="number" hidden name="id" value="${dish.id}" />
                    <input type="number" hidden name="act" value="0" />
                    <input type="submit" name="activate" value="Дективировать"/>
                </form>
            </c:if>



            <form method="post" action="<c:url value='/delete_dish'/>">
                <input type="number" hidden name="id" value="${dish.id}" />
                <input type="submit" name="delete" value="Удалить"/>
            </form>

            <form method="get" action="<c:url value='/update_dish'/>">
                <input type="number" hidden name="id" value="${dish.id}" />
                <input type="submit" value="Редактированть"/>
            </form>
        </ul>
        <hr />
    </c:forEach>
    <h3>Добавление нового блюда</h3><br />

    <form method="post" action="<c:url value='/add_dish'/>">

        <label><input type="text" name="dishName"></label>Название<br>
            <select name="dishType">
            <c:forEach var="type" items="${requestScope.menu.dishTypeList}">
                <option value="${type}">${type}</option>
            </c:forEach>
        </select><br>
        <label><input type="int" name="dishCookingTime"></label>Время приготовления<br>
        <label><input type="text" name="dishImagePath"></label>Путь к файлу картинки<br>

        <input type="submit" value="Ok" name="Ok"><br>
    </form>
</div>
</body>
</html>
