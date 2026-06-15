<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>給料</title>
</head>
<body>
	<h1>給料ページ</h1>
	
	<table>
		<tr>
			<th>給与額</th>
			<th>給与月</th>
		</tr>
		
		<c:forEach var="salary" items="${salaryList}">
			<tr>
				<td>${salary.amount}</td>
				<td>${salary.month}</td>
			</tr>
		</c:forEach>
	</table>
	
	<a href="Main">戻る</a>
</body>
</html>