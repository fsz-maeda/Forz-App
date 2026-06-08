<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib preFix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>経費審査</title>
</head>
<body>
	<h1>経費審査</h1>
	<form action="updateExpensesCheck" method="post">
		<input type="hidden" name="expensesId" value="${expenses.expensesId}">
		<p>審査結果</p>
			<label><input type="radio" name="approval" value="承認"> 承認</label>
			<label><input type="radio" name="approval" value="否決"> 否決</label>
		<input type="submit" value="確定">
	</form>
</body>
</html>