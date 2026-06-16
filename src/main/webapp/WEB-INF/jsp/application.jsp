<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>各種申請</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/application.css">

</head>

<body>

<header>
	<div class="header-top">
		<h1><a href="Main">ForzApp</a></h1>
		<div class="header-link">
			<a href="Main">← メインへ</a>
		</div>
	</div>
</header>

<div class="page-title">
	<h2>📄 各種申請</h2>
</div>

<div class="card-container">

	<a class="card" href="AttendanceServlet">
		<h3>🕒 出勤ページ</h3>
		<p>勤怠登録・確認</p>
	</a>

	<a class="card" href="insertExpenses">
		<h3>💰 経費申請</h3>
		<p>経費の申請・提出</p>
	</a>

	<a class="card" href="insertPaidHoliday">
		<h3>🏖 有給申請</h3>
		<p>休暇の申請</p>
	</a>

</div>

</body>
</html>