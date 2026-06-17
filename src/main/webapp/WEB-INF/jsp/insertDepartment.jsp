<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>部署追加</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/insertDepartment.css">

</head>

<body>

<header>
    <div class="header-top">
        <h1><a href="Main">ForzApp</a></h1>
        <div class="header-link">
        	<a href="manageDepartment">部署管理へ戻る</a>
            <button class="hamburger" onclick="toggleMenu()">☰</button>
			<jsp:include page="/WEB-INF/jsp/header.jsp" />
        </div>
    </div>
</header>

<div class="page-title">
	<h2>🏢 部署追加</h2>
</div>

<div class="form-wrapper">

	<div class="form-card">

		<form action="insertDepartmentCheck" method="post">

			<label>部署名</label>
			<input type="text" name="departmentName" required>

			<button type="submit">追加</button>

		</form>

	</div>

</div>

</body>
</html>