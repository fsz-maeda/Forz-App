<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>パスワード変更</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/ChangePassword.css">

</head>

<body>

<header>
	<div class="header-top">
		<h1><a href="Main">ForzApp</a></h1>
		<div class="header-link">
			<button class="hamburger" onclick="toggleMenu()">☰</button>
			<jsp:include page="/WEB-INF/jsp/header.jsp" />
		</div>
	</div>
</header>

<div class="page-title">
	<h2>🔐 パスワード変更</h2>
</div>

<div class="form-wrapper">

	<div class="form-card">

		<!-- エラーメッセージ -->
		<c:if test="${not empty errorMsg}">
			<div class="error-box">
				${errorMsg}
			</div>
		</c:if>

		<form action="${pageContext.request.contextPath}/UpdatePasswordServlet"
		      method="post">

			<label>現在のパスワード</label>
			<input type="password" name="oldPass" required>

			<label>新しいパスワード</label>
			<input type="password" name="newPass" required>

			<label>新しいパスワード（確認）</label>
			<input type="password" name="confirmPass" required>

			<button type="submit">🔄 変更する</button>

		</form>

	</div>

</div>

</body>
</html>