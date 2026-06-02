<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー修正</title>
</head>
<body>
	<h1>ユーザー修正</h1>
	<form action="updateUser" method="post">
		<input type="hidden" name="userId" value="${user.userId }">
		
		<label for="positionName">ポジション名</label> 
		<select id="positionName" name="positionName" required>
			<option value="">選んでください</option>
			<option value="平社員">平社員</option>
			<option value="リーダー">リーダー</option>
			<option value="課長">課長</option>
			<option value="マネージャー">マネージャー</option>
			<option value="執行役員">執行役員</option>
		</select>
		
		<input type="submit" value="更新">
	</form>
	
	<a href="manageUser">戻る</a>
</body>
</html>