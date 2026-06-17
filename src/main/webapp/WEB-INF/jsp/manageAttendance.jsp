<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>勤怠管理</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/manageAttendance.css">

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
	<h2>🕒 勤怠管理</h2>
	<p>社員一覧から勤怠情報を編集できます</p>
</div>

<hr>

<div class="main">

	<div class="table-card">

		<table>

			<thead>
				<tr>
					<th>ID</th>
					<th>名前</th>
					<th>部署</th>
					<th>操作</th>
				</tr>
			</thead>

			<tbody>

				<c:forEach var="emp" items="${attendanceEmployee}">
					<tr>
						<td>${emp.employeeId}</td>
						<td>${emp.name}</td>
						<td>${emp.department}</td>
						<td>
							<form action="manageAttendance" method="post">
								<input type="hidden" name="employeeId" value="${emp.employeeId}">
								<button type="submit" class="btn-edit">編集</button>
							</form>
						</td>
					</tr>
				</c:forEach>

			</tbody>

		</table>

	</div>

</div>

</body>
</html>