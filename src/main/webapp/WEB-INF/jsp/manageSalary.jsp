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
	
		<p>ここでは管理者が給料を管理するページです</p>
		
		<form action="insertSalary" method="post">
			<input type="submit" value="入力">
		</form>
		
		<form action="selectUpdateSalary" method="post">
			<input type="submit" value="修正">
		</form>
		
		<form action="deleteSalary" method="post">
			<input type="submit" value="削除">
		</form>
		
		<table>
			<tr>
				<th>給料ID</th>
				<th>ユーザーID</th>
				<th>支給額</th>
				<th>支給月</th>
			</tr>
			
			<c:forEach var="salary" items="${salaryList}">
				<tr>
					<td>${salary.salaryId}</td>
					<td>${salary.userId}</td>
					<td>${salary.amount}</td>
					<td>${salary.month}</td>
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