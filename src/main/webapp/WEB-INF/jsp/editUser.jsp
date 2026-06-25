<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー修正</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/editUser.css">

</head>

<body>

<header>
    <div class="header-top">
        <h1><a href="Main">ForzApp</a></h1>
        <div class="header-link">
        	<a href="manageUser">戻る</a>
            <button class="hamburger" onclick="toggleMenu()">☰</button>
			<jsp:include page="/WEB-INF/jsp/header.jsp" />
        </div>
    </div>
</header>

<div class="page-title">
	<h2>👤 ユーザー修正</h2>
</div>

<div class="edit-wrapper">

	<!-- 左：フォーム -->
	<div class="edit-card">

		<form action="updateUser" method="post">

			<input type="hidden" name="employeeId" value="${employee.employeeId}">

			<label>役職ID</label>
			<select name="positionId">
				<c:forEach var="position" items="${positionList}">
					<option value="${position.positionId}"
						${employee.position == position.positionId ? 'selected' : ''}>
						${position.positionName}
					</option>
				</c:forEach>
			</select>
			
			<br>

			<label>部署ID</label>
			<select name="departmentId">
				<c:forEach var="department" items="${departmentList}">
					<option value="${department.departmentId}"
						${employee.department == department.departmentId ? 'selected' : ''}>
						${department.departmentName}
					</option>
				</c:forEach>
			</select>
			
			<br>

			<label>入社日</label>
			<input type="date" name="enter"
			       value="${employee.enter}" required>

			<label>有給日数</label>
			<input type="number" name="remainPaidHoliday"
			       value="${employee.remainPaidHoliday}" required>

			<label>管理者権限</label>
			<div class="radio-group">
				<label>
					<input type="radio" name="management" value="true"
						${employee.management ? 'checked' : ''}>許可
				</label>

				<label>
					<input type="radio" name="management" value="false"
						${!employee.management ? 'checked' : ''}>未許可
				</label>
			</div>

			<button type="submit">更新</button>

		</form>

	</div>

	<!-- 右：マスタ情報 -->
	<div class="master-area">

		<div class="master-card">
			<h3>📌 役職一覧</h3>

			<c:forEach var="position" items="${positionList}">
				<div class="master-item">
					<span>${position.positionId}</span>
					<span>${position.positionName}</span>
				</div>
			</c:forEach>

		</div>

		<div class="master-card">
			<h3>🏢 部署一覧</h3>

			<c:forEach var="department" items="${departmentList}">
				<div class="master-item">
					<span>${department.departmentId}</span>
					<span>${department.departmentName}</span>
				</div>
			</c:forEach>

		</div>

	</div>

</div>

</body>
</html>