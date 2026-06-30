<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>プロフィール</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/profile.css">

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

<div class="profile-page">

	<h2 class="title">👤 社員プロフィール</h2>

	<div class="profile-card">

		<div class="profile-top">
			<img src="${employee.photoPath}" class="profile-img">

			<div class="profile-name">
				<h3>ようこそ、${employee.name} さん</h3>
			</div>
		</div>

		<div class="profile-info">

			<p><span>ID</span>${employee.employeeId}</p>
			<p><span>氏名</span>${employee.name}</p>
			<c:forEach var="position" items="${positionList }">
				<c:if test="${employee.position == position.positionId }">
					<p><span>役職</span>${position.positionName}</p>
				</c:if>
				
			</c:forEach>
			
			<c:forEach var="department" items="${departmentList }">
				<c:if test="${employee.department == department.departmentId }">
					<p><span>部署</span>${department.departmentName}</p>
				</c:if>
			</c:forEach>

		</div>

		<div class="profile-intro">
			<h4>📝 自己紹介</h4>
			<p>${empty employee.intro ? "まだ自己紹介がありません" : employee.intro}</p>
		</div>

	</div>

</div>

</body>
</html>