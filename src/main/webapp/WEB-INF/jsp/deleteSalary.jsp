<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>給料削除</title>
</head>
<body>
	<h1>給料削除</h1>
		<table>
			<tr>
				<th>給料ID</th>
				<th>ユーザーID</th>
				<th>支給額</th>
				<th>支給月</th>
				<th>削除</th>
			</tr>
			
			<c:forEach var="salary" items="${salaryList}">
				<tr>
					<td>${salary.salaryId}</td>
					<td>${salary.userId}</td>
					<td>${salary.amount}</td>
					<td>${salary.month}</td>
					
					<td>
						<form action="deleteSalaryCheck" method="post" onsubmit="return confirm('削除しますか？')">
							<input type="hidden" name="salaryId" value="${salary.salaryId}">
							<input type="submit" value="削除">
						</form>
					</td>
				</tr>
			</c:forEach>
		</table>
</body>
</html>