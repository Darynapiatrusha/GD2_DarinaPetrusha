<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="local" var="loc" />
<fmt:message bundle="${loc}" key="command.name.add" var="add_command" />
<fmt:message bundle="${loc}" key="label.name.title" var="title_label" />
<fmt:message bundle="${loc}" key="label.name.brief" var="brief_label" />
<fmt:message bundle="${loc}" key="label.name.content"
	var="content_label" />


<div class="content">

	<form name="save_news" action="Controller" method="post">
		<input type="hidden" name="command" value="save_news"> <input
			type="hidden" name="status" value="active"> <label><c:out
				value="${title_label}" /></label><br> <input type="textarea"
			name="title"><br> <label><c:out
				value="${brief_label}" /></label><br> <input type="textarea"
			name="brief"><br> <label><c:out
				value="${content_label}" /></label><br>
		<textarea name="content"><c:out value="" /></textarea>
		<br> <input type="submit" value="${add_command}">
	</form>

</div>

