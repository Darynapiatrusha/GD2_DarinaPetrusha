<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="local" var="loc" />

<fmt:message bundle="${loc}" key="button.name.ru" var="ru_button" />
<fmt:message bundle="${loc}" key="button.name.en" var="en_button" />
<fmt:message bundle="${loc}" key="button.name.signOut"
	var="signOut_button" />

<div class="header">
	<div class="heeader-logo">
		<img class="logo" src="img/logo.png">
	</div>
	<div class="header-name">
		<h2>NEWS MANAGEMENT</h2>
	</div>

	<div class="header-lang" align="right">

		<form action="Controller?command=local" method="post">
			<input type="hidden" name="local" value="ru"/> <input type="submit"
				value="${ru_button}" /><br />
		</form>
		<form action="Controller?command=local" method="post">
			<input type="hidden" name="local" value="en"/> <input type="submit"
				value="${en_button}" /><br />
		</form>
		<c:if test="${sessionScope.userId != null}">
			<form action="Controller" method="post">
				<input type="hidden" name="command" value="sign_out" /> <input
					type="submit" value="${signOut_button}" /><br />
			</form>
		</c:if>
	</div>

</div>
