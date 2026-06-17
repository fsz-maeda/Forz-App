<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Profile</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/MyProfile.css">

</head>

<body>

<header>
    <div class="header-top">
        <h1><a href="Main">ForzApp</a></h1>
        <div class="header-link">
            <button class="hamburger" onclick="toggleMenu()">☰</button>
			<jsp:include page="/WEB-INF/jsp/header.jsp" />
        </div>
    </div>
</header>


<div class="profile-wrapper">

	<!-- 上部カード -->
	<div class="profile-card">

		<div class="profile-left">
			<img src="${pageContext.request.contextPath}/${employee.photoPath}" class="profile-img">
		</div>

		<div class="profile-right">
			<h2>${employee.name}</h2>
			<p>社員ID：${employee.employeeId}</p>
			<p>部署：${employeePosition.departmentName}</p>
			<p>役職：${employeePosition.positionName}</p>
		</div>

	</div>

	<!-- 自己紹介 -->
	<div class="section-card">
		<h3>📝 自己紹介</h3>
		<p>${employee.intro}</p>
	</div>

	<!-- アクション -->
	<div class="action-grid">

		<a class="action-card" href="AttendanceServlet">
			🕒 出勤ページ
		</a>

		<a class="action-card" href="salary">
			💰 給料ページ
		</a>

	</div>

	<!-- 設定 -->
	<div class="section-card">

		<h3>⚙ 設定</h3>

		<div class="setting-list">
			<a href="EditIntroPageServlet">自己紹介編集</a>
			<a href="ChangePhotoServlet">写真変更</a>
			<a href="ChangePasswordServlet">パスワード変更</a>
		</div>

	</div>

</div>

</body>
</html>