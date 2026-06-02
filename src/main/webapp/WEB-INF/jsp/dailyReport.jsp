<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>日報</title>
</head>
<body>
	<h1>日報</h1>

<form action="DailyReportPostServlet" method="get">
<input type="submit" value="新規記事作成">
</form>

	
	<c:forEach var="report" items="${reportList}">
	    <p>${report.date} : ${report.content}</p>
	</c:forEach>
	
	<a href="Main">メイン画面へ</a>
	<a href="Home">ホーム画面へ</a>
</body>
</html>