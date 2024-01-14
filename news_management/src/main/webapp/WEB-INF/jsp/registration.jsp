<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="local" var="loc" />

<fmt:message bundle="${loc}" key="label.name.registration" var="registration_label" />
<fmt:message bundle="${loc}" key="label.name.name" var="name_label" />
<fmt:message bundle="${loc}" key="label.name.surname" var="surname_label" />
<fmt:message bundle="${loc}" key="label.name.email" var="email_label" />
<fmt:message bundle="${loc}" key="label.name.login" var="login_label" />
<fmt:message bundle="${loc}" key="label.name.password" var="password_label" />
<fmt:message bundle="${loc}" key="label.name.forgotpassword" var="forgotpassword_label" />
<fmt:message bundle="${loc}" key="command.name.registration" var="registration_command" />
<fmt:message bundle="${loc}" key="message.error.auth" var="message_error_auth" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Регистрация - news management</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<div class="header">
		<c:import url="/WEB-INF/jsp/layers/header.jsp" />
	</div>
	
	<c:if test="${not empty param.error_message}">
		<div class="info-message">
			<p>Ошибка при регистрации, попробуйте еще раз!</p>
		</div>
	</c:if>
	
	<div class="registration-container">
		<div class="frame">
			<h1><c:out value="${registration_label}" /></h1>
			<form name="registration" action="Controller" method="post">
				<input type="hidden" name="command" value="registration">
				
				<label><b><c:out value="${name_label}" /></b></label><br>
				<input type="text" name="name" required>
				
				<label><b><c:out value="${surname_label}" /></b></label><br>
				<input type="text" name="surname" required><br> 
				
				<label><b><c:out value="${email_label}" /></b></label><br>
				<input type="text" name="email" required> 
				
				<label><b><c:out value="${login_label}" /></b></label><br>
				<input type="text" name="login" required>

				<label><b>Пароль</b></label><br>
				<input type="password" name="password" required><br>
				
				<div class="authorization-btn">
					<a href="Controller?command=show_auth_page">Уже есть аккаунт?</a>
				</div>
				
				<input type="submit" class=btn value=<c:out value="${registration_command}" />> <br>
			</form>
		</div>
	</div>
</body>
</html>