<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="local" var="loc" />

<fmt:message bundle="${loc}" key="label.name.authorization"
	var="authorization_label" />
<fmt:message bundle="${loc}" key="label.name.login" var="login_label" />
<fmt:message bundle="${loc}" key="label.name.password"
	var="password_label" />
<fmt:message bundle="${loc}" key="label.name.forgotpassword"
	var="forgotpassword_label" />
<fmt:message bundle="${loc}" key="command.name.registration"
	var="registration_command" />
	<fmt:message bundle="${loc}" key="command.name.authorization"
	var="authorization_command" />
<fmt:message bundle="${loc}" key="message.error.auth"
	var="message_error_auth" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>News management</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<div class="header">
		<c:import url="/WEB-INF/jsp/layers/header.jsp" />
	</div>
	<c:if test="${not empty param.error_message}">
		<div class="info-message">
			<p>Ошибка при авторизации, попробуйте еще раз!</p>
		</div>
	</c:if>

	<div class="auth-container">
		<div class="frame">
			<h1>
				<c:out value="${authorization_label}" />
			</h1>
			<form name="sign_in" action="Controller" method="post">
				<input type="hidden" name="command" value="sign_in"> 
				<label><b><c:out value="${login_label}" /></b></label><br>
				 
				<input type="text" name="login" > 
				<label><b><c:out value="${password_label}" /></b></label><br>
				
				 <input type="password"	name="password" ><br>
				<div class="forgot-password-btn"><c:out value="${forgotpassword_label}" /></div>
				
				<div class="registration-btn">
					<a href="Controller?command=show_registration_page"><c:out value="${registration_command}" /></a>
				</div>
				<input type="submit" class=btn
					value=<c:out value="${authorization_command}"/>> <br>
			</form>
		</div>
	</div>
</body>
</html>