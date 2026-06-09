<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>経費管理</title>
</head>
<body>
	<h1>経費管理</h1>
	
	<c:if test="${updateExpensesMSg != null}">
		${updateExpensesMsg}
		<c:remove  var="updateExpensesMsg" scope="session"/>
	</c:if>
	
	<h2>未承認経費</h2>
	
	<table>
		<tr>
			<th>経費ID</th>
			<th>ユーザーID</th>
			<th>金額</th>
			<th>詳細</th>
			<th>承認</th>
			<th></th>
		</tr>
		
		<c:forEach var="unapproval" items="${unapprovaledList}">
			<tr>
				<td>${unapproval.expensesId}</td>
				<td>${unapproval.employeeId}</td>
				<td>${unapproval.amount}</td>
				<td>${unapproval.detail}</td>
				<td>未承認</td>
				
				<td>
					<form action="updateExpenses" method="post">
						<input type="hidden" name="expensesId" value="${unapproval.expensesId}">
						<input type="submit" value="審査">
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>
	
	<h2>承認済経費</h2>
	
	<table>
		<tr>
			<th>経費ID</th>
			<th>ユーザーID</th>
			<th>金額</th>
			<th>詳細</th>
			<th>承認</th>
			<th></th>
		</tr>
		
		<c:forEach var="approval" items="${approvaledList}">
			<tr>
				<td>${approval.expensesId}</td>
				<td>${approval.employeeId}</td>
				<td>${approval.amount}</td>
				<td>${approval.detail}</td>
				<td>${approval.approval}</td>
				
				<td>
					<form action="updateExpenses" method="post">
						<input type="hidden" name="expensesId" value="${approval.expensesId}">
						<input type="submit" value="修正">
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>
	
	<c:if test="${updateExpensesMsg != null}">
		${updateExpensesMsg}
		<c:remove var="updateExpensesMsg" scope="session"/><br>
	</c:if>
	
	<a href="admin">戻る</a>
</body>
</html>