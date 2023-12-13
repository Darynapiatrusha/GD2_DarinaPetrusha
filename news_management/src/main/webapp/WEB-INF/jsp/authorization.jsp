<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Авторизация - news management</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<div class="header">
		<div class="heeader-logo">
			<img class="logo" src="img/logo.png">
		</div>
		<div class="header-name">
			<h2>NEWS MANAGEMENT</h2>
		</div>
		<div class="header-lang">
			<p>Russian</p>
			<p>English</p>
		</div>
	</div>
	<%
		if (request.getParameter("error_message") != null) {
		%>
		<div class="info-message">
			<p>Ошибка при авторизации, попробуйте еще раз!</p>
		</div>
		<%
		}
		%>
	<div class="auth-container">
		<div class="frame">
			<h1>Вход</h1>
			<form name="sign_in" action="Controller" method="post" target="_blank">
				<input type="hidden" name="command" value="sign_in">
				        
			    <label><b>Логин</b></label><br>
				       <input type="text" name="login" required placeholder="Введите логин">

				<label><b>Пароль</b></label><br>
				       <input type="password" name="password" required placeholder="Введите пароль"><br>
				      <div class="forgot-password-btn">Забыли пароль?</div>
				      <div class="registration-btn"><a href="Controller?command=show_registration_page">Зарегистрироваться</a></div>
				<input type="submit" class=btn value="Войти"> <br>
			</form>
		</div>
	</div>
</body>
</html>