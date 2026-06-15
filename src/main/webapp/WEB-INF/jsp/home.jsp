<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ホーム</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/home.css">

</head>

<body>

<div class="home-wrapper">

	<div class="home-card">

		<h1>ForzApp</h1>
		<p class="subtitle">社内業務ポータルシステム</p>

		<div class="btn-area">

			<a class="btn login" href="login">ログイン</a>
			<a class="btn register" href="UserRegister">ユーザー登録</a>

		</div>

		<c:if test="${loginMsg != null}">
			<div class="message info">
				${loginMsg}
			</div>
			<c:remove var="loginMsg" scope="session"/>
		</c:if>

		<c:if test="${registerMsg != null}">
			<div class="message success">
				${registerMsg}
			</div>
		</c:if>

	</div>

</div>

</body>
</html>