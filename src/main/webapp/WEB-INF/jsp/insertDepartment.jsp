<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>部署追加</title>
</head>
<body>
	<h1>部署追加</h1>
	
	<form action="insertDepartmentCheck" method="post">
		部署名<input type="text" name="departmentName"><br>
		<input type="submit" value="追加">
	</form>
	
	<a href="manageDepartment">戻る</a>
</body>
</html>