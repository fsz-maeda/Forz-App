<%@ page language="java"
contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<meta http-equiv="refresh"
      content="3;URL=${pageContext.request.contextPath}/login">

<title>ログアウト</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/logout.css">

</head>

<body>

<div class="logout-wrapper">

	<div class="logout-card">

		<div class="icon">👋</div>

		<h2>ログアウトしました</h2>

		<p class="text">ご利用ありがとうございました。</p>
		<p class="sub">3秒後にログイン画面へ移動します...</p>

		<div class="loader"></div>

	</div>

</div>

</body>
</html>