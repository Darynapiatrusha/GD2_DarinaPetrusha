<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="local" var="loc" />

<fmt:message bundle="${loc}" key="command.name.view" var="view_command" />
<fmt:message bundle="${loc}" key="command.name.edit" var="edit_command" />
<fmt:message bundle="${loc}" key="command.name.delete"
	var="delete_command" />

<div class="content">
	<c:forEach var="news" items="${requestScope.listOfNews}">
		<div class="content-item">
			<div class="content-item-title">
				<h4>${news.title}</h4>
			</div>
			<div class="content-item-date">
				<p>${news.date}</p>
			</div>
			<div class="content-item-text">
				<p>${news.content}</p>
			</div>
			<c:choose>
				<c:when test="${sessionScope.userId != null}">
					<div class="content-item-manage">
						<p>
							<a href="Controller?command=show_edit_news&id=${news.id}&edit=1"><c:out
									value="${edit_command}" /></a>
						</p>
						<p>
							<a href="Controller?command=show_news_view&id=${news.id}&view"><c:out
									value="${view_command}" /></a>
						</p>
					</div>
				</c:when>
				<c:otherwise>
					<div class="content-item-manage">
						<p>
							<a href="Controller?command=show_news_view&id=${news.id}&view"><c:out
									value="${view_command}" /></a>
						</p>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
	</c:forEach>
	<div class="pagination">
		<c:forEach var="pagination" items="${requestScope.numberOfPages}">
			<a href="Controller?command=show_news_list&page=${pagination}">${pagination}</a>
		</c:forEach>
	</div>

</div>