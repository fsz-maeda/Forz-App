<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規記事作成</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/dailyReportPost.css">

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

<c:if test="${not empty sessionScope.dailyReportErrorMsg}">
	<div class="message danger">
		${sessionScope.dailyReportErrorMsg}
	</div>
	<c:remove var="dailyReportErrorMsg" scope="session"/>
</c:if>

<div class="post-wrapper">

	<div class="post-card">

		<h2>📝 新規記事作成</h2>

		<form action="DailyReportPostServlet" method="post">

			<input type="hidden" name="action" value="post">

			<label>タイトル</label>
			<input type="text" name="title" placeholder="タイトルを入力" required>

			<label>種類</label>
			<select name="dailyType">
				<option value="日報">日報</option>
				<option value="週間レポート">週間レポート</option>
			</select>

			<label>内容</label>
			<textarea name="content" rows="12" placeholder="内容を入力してください" required></textarea>

			<div class="foot">
				<a href="dailyReportPage" class="back-btn">戻る</a>
			    <button type="submit">投稿</button>
			</div>


		</form>

	</div>

</div>

</body>
</html>