<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="local" var="loc" />

<fmt:message bundle="${loc}" key="label.name.news"
	var="news_label" />
<fmt:message bundle="${loc}" key="command.name.newsList"
	var="newsList_command" />
<fmt:message bundle="${loc}" key="command.name.addNews"
	var="addNews_command" />
<fmt:message bundle="${loc}" key="command.name.registration"
	var="registration_command" />
<fmt:message bundle="${loc}" key="command.name.authorization"
	var="authorization_command" />

<c:choose>
	<c:when test="${sessionScope.userId != null}">
		<div class="navigation">
			<div class="navigatinon-header">
				<h4><c:out value="${news_label}"/></h4>
			</div>
			<div class="navigatinon-content">
				<p>
					<a href="Controller?command=show_news_list&page=1"><c:out value="${newsList_command}"/></a>
				</p>
				<p>
					<a href="Controller?command=create_news&add=1"><c:out value="${addNews_command}"/></a>
				</p>
			</div>
		</div>
	</c:when>
	<c:otherwise>
		<div class="navigation">
			<div class="navigatinon-header"></div>
			<div class="navigatinon-content">
				<p>
					<a href="Controller?command=show_registration_page"><c:out value="${registration_command}"/></a>
				<p>
				<p>
					<a href="Controller?command=show_auth_page"><c:out value="${authorization_command}"/></a>
				</p>
			</div>
		</div>
	</c:otherwise>
</c:choose>

