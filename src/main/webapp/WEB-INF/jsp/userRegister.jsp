<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー登録</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/userRegister.css">

</head>

<body>

<div class="register-wrapper">

	<div class="register-card">

		<h1>ユーザー登録</h1>
		<p class="sub">アカウント情報を入力してください</p>

		<form action="UserRegister" method="post">

			<label>名前</label>
			<input type="text" name="name" required>

			<label>メール</label>
			<input type="email" name="mail" required>

			<label>パスワード</label>
			<input type="password" name="pass" required>

			<button type="submit">登録</button>

		</form>

	</div>

</div>

</body>
</html>