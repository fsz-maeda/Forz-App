<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>部署修正</title>
</head>
<body>
	<h1>部署修正</h1>
	
	<form action="updateDepartmentCheck" method="post">
		<input type="hidden" name="departmentId" value="${department.departmentId}">
		部署名<input type="text" name="departmentName" value="${department.departmentName}">
		<input type="submit" value="修正">
	</form>
	
	<a href="manageDepartment">戻る</a>
</body>
</html>