<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>役職修正</title>
</head>
<body>
	<h1>役職修正</h1>
	
	<form action="updatePositionCheck" method="post">
		<input type="hidden" name="positionId" value="${position.positionId}">
		<input type="number" name="newPositionId" value="${position.positionId}">
		<input type="text" name="positionName" value="${position.positionName}">
		<input type="submit" value="更新">
	</form>
	
	<a href="managePosition">戻る</a>
</body>
</html>