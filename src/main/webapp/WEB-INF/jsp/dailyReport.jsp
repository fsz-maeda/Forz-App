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
	<div class="new-post">
		<form action="DailyReportPostServlet" method="get">
			<input type="submit" value="新規記事作成">
		</form>
	</div>
	
<c:forEach var="r" items="${reportList}">

    <div class="report-list" style="border:1px solid #ccc; margin:10px; padding:10px;">
        <p>投稿者：${r.userName}</p>
        <p>種別：${r.reportType}</p>
        <p>タイトル：${r.title}</p>
        <p>内容：${r.content}</p>
        <p>投稿日：${r.createdAt}</p>
        <p>いいね：${r.likes}</p>
        
	<form action="DailyReportLikeServlet" method="post">
	    <input type="hidden" name="dailyReportId" value="${r.dailyReportId}"/>
	    <c:choose>
	        <c:when test="${r.liked}">
	            <button type="button" disabled>いいね済み</button>
	        </c:when>
	
	        <c:otherwise>
	            <button type="submit">いいね</button>
	        </c:otherwise>
	    </c:choose>
	</form>

    </div>

</c:forEach>
	
	<a href="Main">メイン画面へ</a>
	<a href="Home">ホーム画面へ</a>
</body>
</html>