<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規記事作成</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/dailyReportPost.css">
</head>
<body>
	<h1>新規記事作成</h1>
	
	<c:if test="${not empty sessionScope.dailyReportErrorMsg }">
		<p>${sessionScope.dailyReportErrorMsg}</p>
	</c:if>
	
	<c:remove var="dailyReportErrorMsg" scope="session"/>
	
	<form action="DailyReportPostServlet" method="post">
	<input type="hidden" name="action" value="post">

	タイトル<input type="text" name="title" placeholder="タイトルを入力してください"required>
	
	<select name="dailyType">
		<option value="日報">日報</option>
		<option value="週間レポート">週間レポート</option>
	</select>
	
	<textarea name="content" rows="50" cols="100" placeholder="日報または週間レポートを入力してください"required></textarea>
	<input type="submit" value="投稿">
	
	</form>
	

	
</body>
</html>