<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Forz-App</title>
</head>
<body>

	<h1>Forz-App</h1>

<hr>
<h2>今日の励ましの言葉</h2>
<p>
    ${motivation}
</p>
<hr>
<h2>📋 Menu</h2>
<ul>
    <li><a href="event">🎉 イベント</a></li>

    <li><a href="NoticeServlet">📢 お知らせ</a></li>

    <li><a href="dailyReportPage">📝 日報</a></li>

    <li><a href="EmployeeListServlet">👥 社員一覧</a></li>

    <li><a href="ChatServlet">💬 Chat</a></li>

    <li><a href="MyProfileServlet">👤 プロフィール</a></li>
    
    <c:if test="${loginUser.getPositionId() >= 5}">
		<li><a href="admin">⚙ 管理者ページ</a></li>
	</c:if>
</ul>
<hr>
<h2>🔔 Notifications</h2>
<ul>
    <li>誕生日のお知らせ</li>
    <li>イベントのお知らせ</li>
    <li>システム通知</li>
</ul>
<hr>
<h2>My Profile</h2>

<img src="images/default.png"width="120"height="120">

<br><br>

<a href="MyProfileServlet">プロフィールを見る</a>
<hr>
<p>© Forz-App</p>
</body>
</html>