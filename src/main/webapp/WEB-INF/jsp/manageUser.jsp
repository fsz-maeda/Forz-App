<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー管理</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/manageUser.css">

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
	<h2>👥 ユーザー管理</h2>
	<p>社員情報の編集・削除</p>
</div>

<hr>

<div class="main">

	<div class="table-card">

		<table>

			<thead>
				<tr>
					<th>ID</th>
					<th>名前</th>
					<th>メール</th>
					<th>役職</th>
					<th>部署</th>
					<th>入社日</th>
					<th>有給</th>
					<th>権限</th>
					<th>修正</th>
					<th>削除</th>
				</tr>
			</thead>

			<tbody>

				<c:forEach var="employee" items="${employeePositionList}">
					<tr>
						<td>${employee.employeeId}</td>
						<td class="name">${employee.name}</td>
						<td>${employee.mail}</td>
						<td>${employee.positionName}</td>
						<td>${employee.departmentName}</td>
						<td>${employee.enter}</td>
						<td>${employee.remainPaidHoliday}</td>

						<td>
							<c:choose>
								<c:when test="${employee.management == true}">
									<span class="badge admin">許可</span>
								</c:when>
								<c:otherwise>
									<span class="badge normal">未許可</span>
								</c:otherwise>
							</c:choose>
						</td>

						<td>
							<form action="editUser" method="post">
								<input type="hidden" name="employeeId" value="${employee.employeeId}">
								<button class="btn-edit">修正</button>
							</form>
						</td>

						<td>
							<form action="deleteUser" method="post"
								  onsubmit="return confirm('削除しますか？')">
								<input type="hidden" name="employeeId" value="${employee.employeeId}">
								<button class="btn-delete">削除</button>
							</form>
						</td>

					</tr>
				</c:forEach>

			</tbody>

		</table>

	</div>

</div>

<!-- メッセージ -->
<c:if test="${updateUserMsg != null}">
	<div class="message info">
		${updateUserMsg}
	</div>
	<c:remove var="updateUserMsg" scope="session"/>
</c:if>

<c:if test="${deleteUserMsg != null}">
	<div class="message danger">
		${deleteUserMsg}
	</div>
	<c:remove var="deleteUserMsg" scope="session"/>
</c:if>

<br>
</body>
</html>