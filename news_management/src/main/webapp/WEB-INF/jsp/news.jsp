<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="local" var="loc" />

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>News Management</title>
<link rel="stylesheet" href='./css/style.css'>
</head>
<body>
	<div class="header">
		<c:import url="/WEB-INF/jsp/layers/header.jsp" />
	</div>
	<div class="main-container">
		<c:import url="/WEB-INF/jsp/layers/menu.jsp" />
		<c:choose>
			<c:when test="${param.edit !=null}">
				<c:import url="/WEB-INF/jsp/layers/news_edit.jsp" />
			</c:when>
			<c:when test="${param.add !=null}">
				<c:import url="/WEB-INF/jsp/layers/news_add.jsp" />
			</c:when>
			<c:otherwise>
				<c:import url="/WEB-INF/jsp/layers/news_content.jsp" />
			</c:otherwise>
		</c:choose>
	</div>
	<style>
.main-container {
	width: 100%;
}

form {
	width: 100%;
	padding: 0;
}

.content {
	flex-grow: 1;
}

input[type="textarea"], textarea {
	width: 100%;
}

textarea {
	min-height: 200px;
}
</style>
</body>
</html>