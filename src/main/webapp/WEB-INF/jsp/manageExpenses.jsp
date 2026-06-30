<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>経費管理</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/manageExpenses.css">

</head>

<body>

<header>
    <div class="header-top">
        <h1><a href="Main">ForzApp</a></h1>
        <div class="header-link">
        	<a href="admin">管理者ページへ戻る</a>
            <button class="hamburger" onclick="toggleMenu()">☰</button>
			<jsp:include page="/WEB-INF/jsp/header.jsp" />
        </div>
    </div>
</header>

<div class="word">
	<h2>💰 経費管理</h2>
	<p>申請の審査・承認管理</p>
</div>

<hr>

<div class="main">

	<!-- 未承認 -->
	<div class="table-card pending">

		<h3>🕒 未承認経費</h3>

		<table>
			<thead>
				<tr>
					<th>ID</th>
					<th>ユーザー</th>
					<th>金額</th>
					<th>詳細</th>
					<th>状態</th>
					<th>操作</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach var="unapproval" items="${unapprovedList}">
					<tr>
						<td>${unapproval.expensesId}</td>
						
						<c:forEach var="employee" items="${employeeList}">
							<c:if test="${unapproval.employeeId == employee.employeeId}">
								<td>${employee.name}</td>
							</c:if>
						</c:forEach>
						
						<td>${unapproval.amount}</td>
						<td>${unapproval.detail}</td>
						<td><span class="status pending">未承認</span></td>
						<td>
							<form action="updateExpenses" method="post">
								<input type="hidden" name="expensesId" value="${unapproval.expensesId}">
								<button type="submit" class="btn-approve">審査</button>
							</form>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

	</div>

	<!-- 承認済 -->
	<div class="table-card approved">

		<h3>✅ 承認済経費</h3>

		<table>
			<thead>
				<tr>
					<th>ID</th>
					<th>ユーザー</th>
					<th>金額</th>
					<th>詳細</th>
					<th>状態</th>
					<th>操作</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach var="approval" items="${approvedList}">
					<tr>
						<td>${approval.expensesId}</td>
						
						<c:forEach var="employee" items="${employeeList}">
							<c:if test="${approval.employeeId == employee.employeeId}">
								<td>${employee.name}</td>
							</c:if>
						</c:forEach>
						
						<td>${approval.amount}</td>
						<td>${approval.detail}</td>
						<td><span class="status approved">${approval.approval}</span></td>
						<td>
							<form action="updateExpenses" method="post">
								<input type="hidden" name="expensesId" value="${approval.expensesId}">
								<button type="submit" class="btn-edit">修正</button>
							</form>
						</td>
					</tr>
				</c:forEach>
			</tbody>

		</table>

	</div>

</div>

<!-- メッセージ -->
<c:if test="${updateExpensesMsg != null}">
	<div class="message info">
		${updateExpensesMsg}
	</div>
	<c:remove var="updateExpensesMsg" scope="session"/>
</c:if>

</body>
</html>