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
	<h1>更新給料データの選択</h1>
		<table>
			<tr>
				<th>給料ID</th>
				<th>ユーザーID</th>
				<th>支給額</th>
				<th>支給月</th>
				<th>選択</th>
			</tr>
			
			<c:forEach var="salary" items="${salaryList}">
				<tr>
					<td>${salary.salaryId}</td>
					<td>${salary.userId}</td>
					<td>${salary.amount}</td>
					<td>${salary.month}</td>
					
					<td>
						<form action="updateSalary" method="post">
							<input type="hidden" name="salaryId" value="${salary.salaryId}">
							<input type="submit" value="選択">
						</form>
					</td>
				</tr>
			</c:forEach>
			</form>
		</table>
</body>
</html>