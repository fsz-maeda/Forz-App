<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>経費審査</title>
</head>
<body>
	<h1>経費審査</h1>
	
	<table>
		<tr>
			<th>経費ID</th>
			<th>従業員ID</th>
			<th>金額</th>
			<th>詳細</th>
			<th>承認</th>
		</tr>
		
		<tr>
			<td>${expenses.expensesId}</td>
			<td>${expenses.employeeId}</td>
			<td>${expenses.amount}</td>
			<td>${expenses.detail}</td>
			
			<td>
				<c:choose>
					<c:when test="${expenses.approval == null}">
						未承認
					</c:when>
					<c:otherwise>
						${expenses.approval}
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
	</table>
	
	<form action="updateExpensesCheck" method="post">
		<input type="hidden" name="expensesId" value="${expenses.expensesId}">
		<p>審査結果</p>
			<label><input type="radio" name="approval" value="承認"> 承認</label>
			<label><input type="radio" name="approval" value="否決"> 否決</label><br>
		<input type="submit" value="確定">
	</form>
</body>
</html>