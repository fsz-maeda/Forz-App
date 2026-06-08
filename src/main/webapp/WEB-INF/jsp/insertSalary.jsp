<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>給料入力</title>
</head>
<body>
	<h1>給料入力</h1>
	
	<form action="insertSalaryCheck" method="post">
		<ul>
			<li>給料額<input type="number" name="amount"></li>
			<li>支給月<input type="number" name="month"></li>
		</ul>
		
		<p>下記のユーザーリストから、給料を入力するユーザーを選択してください</p>
	
		<table>
			<tr>
				<th></th>
				<th>ユーザーID</th>
				<th>ユーザー名</th>
				<th>メールアドレス</th>
				<th>役職</th>
			<tr>
			
			<c:forEach var="employee" items="${employeePositionList }">
				<tr>
					<td><input type="radio" name="userId" value="${employee.employeeId }" required></td>
				    <td>${employee.employeeId}</td>
				    <td>${employee.name}</td>
				    <td>${employee.mail}</td>
				    <td>${employee.positionName}</td>
				</tr>
			</c:forEach>
		</table>
		
		<input type="submit" value="入力">
		
	</form>
</body>
</html>