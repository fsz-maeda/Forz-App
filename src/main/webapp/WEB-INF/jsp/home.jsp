<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ホーム</title>
</head>
<body>
	<h1>ホーム</h1>
	<a href="login">ログイン</a>
	<a href="UserRegister">ユーザー登録</a>
	
	<c:out value="${registerMsg }" />
</body>
</html>