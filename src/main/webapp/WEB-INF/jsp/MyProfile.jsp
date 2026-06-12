<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Profile</title>
</head>
<body>

	<h1>👤 マイプロフィール</h1>
	<hr>

	<h3>写真</h3>
	<img src="${pageContext.request.contextPath}/${employee.photoPath}"
		width="135" height="150">

	<hr>

	社員ID : ${employee.employeeId}<br><br>

	氏名 : ${employee.name}<br><br>

	部署 : ${employeePosition.departmentName}<br><br>

	役職 : ${employeePosition.positionName}<br><br>

	<hr>

	<h3>📝 自己紹介</h3>
	${employee.intro}

	<hr>

	<h3>出勤情報</h3>
	<a href="${pageContext.request.contextPath}/AttendanceServlet">
		出勤ページ
	</a>

	<hr>

	<h3>給料明細</h3>
	<a href="${pageContext.request.contextPath}/expenses">
		給料ページ
	</a>

	<hr>

	<h3>設定</h3>

	<a href="${pageContext.request.contextPath}/EditIntroPageServlet">
		自己紹介編集<br>
	</a>

	<a href="${pageContext.request.contextPath}/ChangePhotoServlet">
		プロフィール写真変更<br>
	</a>

	<a href="${pageContext.request.contextPath}/ChangePasswordServlet">
		パスワード変更<br>
	</a>

	<a href="${pageContext.request.contextPath}/Main">
		🏠 ホームへ<br>
	</a>

</body>
</html>