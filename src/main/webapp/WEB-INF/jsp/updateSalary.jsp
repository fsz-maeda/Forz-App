<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>給料更新</title>
</head>
<body>
	<h1>給料更新</h1>
		<form action="updateSalaryCheck" method="post">
			<input type="hidden" name="salaryId" value="${salary.salaryId}">
			
			<label>ユーザーID
				<input type="number" name="userId" value="${salary.userId}">
			</label>
			
			<label>支給額
				<input type="number" name="amount" value="${salary.amount}">
			</label>
			
			<label>支給月
				<input type="number" name="month" value="${salary.month}">
			</label>
			
			<input type="submit" value="更新">
		</form>
</body>
</html>