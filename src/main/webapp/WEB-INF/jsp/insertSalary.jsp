<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>給料入力</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/insertSalary.css">

</head>

<body>

<header>
    <div class="header-top">
        <h1><a href="Main">ForzApp</a></h1>
        <div class="header-link">
        	<a href="manageSalary">戻る</a>
            <button class="hamburger" onclick="toggleMenu()">☰</button>
			<jsp:include page="/WEB-INF/jsp/header.jsp" />
        </div>
    </div>
</header>


<h1>💰 給料入力</h1>

<form action="insertSalaryCheck" method="post">

	<!-- 給与入力 -->
	<div class="salary-form">
		<label>給料額</label>
		<input type="number" name="amount" required>

		<label>支給月</label>
		<input type="number" name="month" min="1" max="12" required>
	</div>



	<div class="user-list">
		<h2>ユーザー選択</h2>

		<c:forEach var="employee" items="${employeePositionList}">

			<label class="user-card">

				<input type="radio" name="employeeId"
				       value="${employee.employeeId}" required>

				<div class="user-info">
					<strong>${employee.name}</strong>
					<p>ID: ${employee.employeeId}</p>
					<p>${employee.mail}</p>
					<p>${employee.positionName}</p>
				</div>

			</label>

		</c:forEach>

	</div>

	<div class="form-button">
		<button type="submit">入力</button>
	</div>

</form>

</body>
</html>