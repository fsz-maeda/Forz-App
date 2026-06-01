<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー登録</title>
</head>
<body>
	<h1>ユーザー登録</h1>
	<form action="UserRegister" method="post">
		<input type="text" name="name">名前
		<input type="password" name="pass">パスワード
		<input type="email" name="mail">メール
		<input type="submit">登録
	</form>
</body>
</html>