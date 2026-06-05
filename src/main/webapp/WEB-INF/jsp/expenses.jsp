<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>経費精算</title>
</head>
<body>
	<h1>経費精算</h1>
	
	<h2>経費申請</h2>
	<form action="insertExpenes.jsp" method="post">
		<input type="submit" value="申告画面">
	</form>
	
	<h2>清算済み</h2>
	<table>
		<tr>
			<th>金額</th>
			<th>詳細</th>
		</tr>
		
		<c:forEach var="expenses" items="${expensesList}">
			<tr>
				<td>${expenses.amount}</td>
				<td>${expenses.detail}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>