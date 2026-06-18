<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>有給修正</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/updatePaidholiday.css">
</head>

<body>

<!-- ヘッダー -->
<header>
	<div class="header-top">
		<h1><a href="Main">ForzApp</a></h1>

		<div class="header-link">
			<button class="hamburger" onclick="toggleMenu()">☰</button>
			<jsp:include page="/WEB-INF/jsp/header.jsp" />
		</div>
	</div>
</header>

<div class="container">

	<h1 class="page-title">有給修正</h1>

	<div class="form-card">

		<form action="updatePaidHolidayCheck" method="post">

			<input type="hidden" name="paidHolidayId"
				value="${paidHoliday.paidHolidayId}">

			<input type="hidden" name="employeeId"
				value="${loginUser.employeeId}">

			<div class="form-group">
				<label>休暇区分</label>
				<select name="holidayType">

					<option value="全休"
						${paidHoliday.holidayType == '全休' ? 'selected' : ''}>全休</option>

					<option value="午前休"
						${paidHoliday.holidayType == '午前休' ? 'selected' : ''}>午前休</option>

					<option value="午後休"
						${paidHoliday.holidayType == '午後休' ? 'selected' : ''}>午後休</option>

				</select>
			</div>

			<div class="form-group">
				<label>開始日</label>
				<input type="date" name="startDate"
					value="${paidHoliday.startDate}" required>
			</div>

			<div class="form-group">
				<label>終了日</label>
				<input type="date" name="finishDate"
					value="${paidHoliday.finishDate}" required>
			</div>

			<div class="btn-area">
				<a href="application" class="back-btn">戻る</a>
				<button type="submit">更新</button>
			</div>

		</form>

	</div>

</div>

<script>
function toggleMenu() {
	document.getElementById("sideMenu").classList.toggle("open");
}
</script>

</body>
</html>