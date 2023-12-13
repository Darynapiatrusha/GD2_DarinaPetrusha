<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Регистрация - news management</title>
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
			<p>Ошибка при регистрации, попробуйте еще раз!</p>
		</div>
		<%
		}
		%>
	<div class="registration-container">
		<div class="frame">
			<h1>Регистрация</h1>
			<form name="registration" action="Controller" method="post">
			<input type="hidden" name="command" value="registration">
				<label><b>Имя</b></label><br>
				<input type="text" name="name" required placeholder="Введите Ваше имя">
				
				<label><b>Фамилия</b></label><br>
				<input type="text" name="surname" required placeholder="Введите Вашу фамилию"><br>
				
				<label><b>E-mail</b></label><br>
				<input type="text" name="email" required placeholder="Введите e-mail"> 
				
				<label><b>Логин</b></label><br>
				<input type="text" name="login" required placeholder="Введите логин">
				
				<label><b>Пароль</b></label><br>
				<input type="password" name="password" required placeholder="Введите пароль"><br>
				<div class="authorization-btn"> <a href="Controller?command=show_auth_page">Уже есть аккаунт?</a>
				</div>
				<input type="submit" class=btn value="Зарегистрироваться"> <br>
			</form>
		</div>
	</div>
</body>
</html>