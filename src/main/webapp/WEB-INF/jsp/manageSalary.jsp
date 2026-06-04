<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>給料管理</title>
</head>
<body>
	<h1>給料管理</h1>
	
	<h2>新規給料記入</h2>
		<form action="insertSalary" method="post">
			<input type="submit" value="入力">
		</form>
		
	<h2>既存給料修正</h2>
		<table>
			<tr>
				<th>給料ID</th>
				<th>ユーザーID</th>
				<th>支給額</th>
				<th>支給月</th>
				<th></th>
				<th></th>
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
							<input type="submit" value="修正">
						</form>
					</td>
					
					<td>
						<form action="deleteSalary" method="post" onsubmit="return confirm('削除しますか？')">
							<input type="hidden" name="salaryId" value="${salary.salaryId}">
							<input type="submit" value="削除">
						</form>
					</td>
				</tr>
			</c:forEach>
		</table>
		
		<c:if test="${insertSalaryMsg != null}">
			${insertSalaryMsg}
			<c:remove  var="insertSalaryMsg" scope="session"/>
		</c:if><br>
		
		<c:if test="${updateSalaryMsg != null}">
			${updateSalaryMsg}
			<c:remove  var="updateSalaryMsg" scope="session"/>
		</c:if><br>
		
		<c:if test="${deleteSalaryMsg != null}">
			${deleteSalaryMsg}
			<c:remove  var="deleteSalaryMsg" scope="session"/>
		</c:if><br>
		
		<a href="admin">戻る</a>
</body>
</html>