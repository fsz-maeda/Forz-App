<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>経費申請</title>
</head>
<body>
	<h1>経費申請</h1>
	<h2>申請</h2>
	<form action="insertExpensesCheck" method="post">
		申請額<input type="number" name="amount">
		詳細<input type="text" name="detail">
		<input type="submit" value="申請">
	</form>
	
	<h2>申請履歴</h2>
	<table>
		<tr>
			<th>経費ID</th>
			<th>金額</th>
			<th>詳細</th>
			<th>承認</th>
		</tr>
		
		<c:forEach var="expenses" items="${expensesList}">
			<tr>
				<td>${expenses.expensesId}</td>
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
		</c:forEach>
	</table>
	
	<c:if test="${insertExpensesMsg != null}">
		${insertExpensesMsg}
		<c:remove  var="insertExpensesMsg" scope="session"/>
	</c:if><br>
	
	<a href="Main">戻る</a>
</body>
</html>