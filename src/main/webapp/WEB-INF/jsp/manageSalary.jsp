<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>給料管理</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/manageSalary.css">

</head>

<body>

<header>
	<div class="header-top">
		<h1><a href="admin">ForzApp</a></h1>
		<div class="header-link">
			<p><a href="admin">管理者ページへ</a></p>
		</div>
	</div>
</header>

<div class="word">
	<h2>💰 給料管理</h2>
	<p>支給情報の登録・編集・削除</p>
</div>

<hr>

<div class="main">

	<!-- 追加 -->
	<div class="card">

		<h3>➕ 新規給料入力</h3>

		<form action="insertSalary" method="post">
			<button type="submit" class="btn-primary">入力</button>
		</form>

	</div>

	<!-- 一覧 -->
	<div class="table-card">

		<h3>📋 給料一覧</h3>

		<table>

			<thead>
				<tr>
					<th>ID</th>
					<th>ユーザー</th>
					<th>支給額</th>
					<th>月</th>
					<th>修正</th>
					<th>削除</th>
				</tr>
			</thead>

			<tbody>

				<c:forEach var="salary" items="${salaryList}">
					<tr>
						<td>${salary.salaryId}</td>
						<td>${salary.employeeId}</td>
						<td class="amount">¥ ${salary.amount}</td>
						<td>${salary.month}</td>

						<td>
							<form action="updateSalary" method="post">
								<input type="hidden" name="salaryId" value="${salary.salaryId}">
								<button type="submit" class="btn-edit">修正</button>
							</form>
						</td>

						<td>
							<form action="deleteSalary" method="post"
								  onsubmit="return confirm('削除しますか？')">
								<input type="hidden" name="salaryId" value="${salary.salaryId}">
								<button type="submit" class="btn-delete">削除</button>
							</form>
						</td>

					</tr>
				</c:forEach>

			</tbody>

		</table>

	</div>

</div>

<!-- メッセージ -->
<c:if test="${insertSalaryMsg != null}">
	<div class="message success">${insertSalaryMsg}</div>
	<c:remove var="insertSalaryMsg" scope="session"/>
</c:if>

<c:if test="${updateSalaryMsg != null}">
	<div class="message info">${updateSalaryMsg}</div>
	<c:remove var="updateSalaryMsg" scope="session"/>
</c:if>

<c:if test="${deleteSalaryMsg != null}">
	<div class="message danger">${deleteSalaryMsg}</div>
	<c:remove var="deleteSalaryMsg" scope="session"/>
</c:if>

<br>

</body>
</html>