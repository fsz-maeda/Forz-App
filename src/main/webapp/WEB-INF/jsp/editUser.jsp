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
		<input type="hidden" name="employeeId" value="${employee.employeeId }">
		
		役職名<input type="text" name="positionName" value"=${positionName }>
		部署名<input type="text" name="departmentName" value="${departmentName }">
		入社日<input type="date" name="enter" value="${enter }">
		<p>管理者権限</p>
			<input type="radio" name="management" value="true"> 許可
    		<input type="radio" name="management" value="false"> 不許可
		
		<input type="submit" value="更新">
	</form>
	
	<a href="manageUser">戻る</a>
</body>
</html>