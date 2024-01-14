<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="local" var="loc" />

<fmt:message bundle="${loc}" key="command.name.edit" var="edit_command" />
<fmt:message bundle="${loc}" key="command.name.delete" var="delete_command" />
<fmt:message bundle="${loc}" key="command.name.backToNewsList"
	var="backToNewsList_command" />

<div class="content">
	<div class="content-item-view">
		<div class="content-item-title-view">
			<h4>${newsItem.title}</h4>
		</div>
		<div class="content-item-date-view">
			<p>${newsItem.date}</p>
		</div>
		<div class="content-item-text-view">
			<p>${newsItem.content}</p>
		</div>
	</div>
	<div class="content-item-manage">
	<c:choose>
		<c:when test="${sessionScope.userId != null}">
		<p>
			<a href="Controller?command=show_edit_news&id=${param.id}&edit=1" ><c:out value="${edit_command}" /></a>
			</p>
		<p>
				<a href="Controller?command=delete_news&id=${param.id}" ><c:out value="${delete_command}" /></a>
		</p>
		</c:when>
		<c:otherwise>
					<div class="content-item-manage">
						<p>
							<a href="Controller?command=show_news_list&page=1" ><c:out value="${backToNewsList_command}" /></a>
						</p>
					</div>
				</c:otherwise>
			</c:choose>
	</div>
</div>