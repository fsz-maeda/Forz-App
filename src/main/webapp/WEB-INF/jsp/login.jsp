<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">

</head>

<body>

<div class="login-wrapper">

	<div class="login-card">

		<h1>ログイン</h1>
		<p class="sub">アカウント情報を入力してください</p>

		<form action="LoginCheck" method="post">

			<label>名前</label>
			<input type="text" name="name" required>

			<label>パスワード</label>
			<input type="password" name="pass" required>

			<button type="submit">ログイン</button>

		</form>

	</div>

</div>

</body>
</html>