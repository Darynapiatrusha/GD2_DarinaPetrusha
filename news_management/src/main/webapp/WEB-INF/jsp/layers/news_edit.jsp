<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="local" var="loc" />
<fmt:message bundle="${loc}" key="command.name.edit" var="edit_command" />
<fmt:message bundle="${loc}" key="command.name.delete"
	var="delete_command" />

<div class="content">

	<form name="edit_news" action="Controller" method="post">
		<input type="hidden" name="command" value="edit_news"> <input
			type="hidden" name="date" value="${newsItem.date}"> <input
			type="hidden" name="status" value="${newsItem.status}"> <input
			type="hidden" name="userId" value="${newsItem.userId}"> <input
			type="hidden" name="id" value="${newsItem.id}"> <label>title</label><br>
		<input type="textarea" name="title" value="${newsItem.title}"><br>

		<label>brief</label><br> <input type="textarea" name="brief"
			value="${newsItem.brief}"><br> <label>content</label><br>
		<textarea name="content"><c:out value="${newsItem.content}" /></textarea>
		<br> <input type="submit" value="Редактировать">
	</form>

</div>

