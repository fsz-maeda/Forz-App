<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>役職入力</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/insertPosition.css">

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

<div class="page-title">
	<h2>📌 役職入力</h2>
</div>

<div class="form-wrapper">

	<div class="form-card">

		<form action="insertPositionCheck" method="post">

			<label>役職ID</label>
			<input type="number" name="positionId" required>

			<label>役職名</label>
			<input type="text" name="positionName" required>

			<button type="submit">入力</button>

		</form>

	</div>

</div>

</body>
</html>