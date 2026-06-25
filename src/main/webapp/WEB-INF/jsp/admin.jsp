<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理者ページ</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css">

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

<!-- タイトル -->
<div class="word">
	<h2>⚙ 管理者ダッシュボード</h2>
	<p>各機能を選択してください</p>
</div>

<hr>

<!-- メイン -->
<div class="main">

	<div class="main-news">

		<!-- ユーザー -->
		<div>
			<h3>👤 ユーザー管理</h3>
			<a class="card-link" href="manageUser">ユーザー管理</a>
		</div>

		<!-- 人事 -->
		<div>
			<h3>💰 人事・給与</h3>

			<a class="card-link" href="manageSalary">給料管理</a>
			<a class="card-link" href="managePosition">役職管理</a>
			<a class="card-link" href="manageDepartment">部署管理</a>
		</div>

		<!-- 業務 -->
		<div>
			<h3>📋 業務管理</h3>

			<a class="card-link" href="manageExpenses">経費管理</a>
			<a class="card-link" href="managePaidHoliday">有給管理</a>
		</div>

	</div>

</div>

<hr>
</body>
</html>