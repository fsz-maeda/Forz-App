<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Forz-App</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
</head>
<body>
	<header>
		<div class="header-top">
			<h1>Forz-App</h1>
			<div class="header-link">
				<p>ユーザネーム :<b>${loginUser.name}</b></p>
				<p>ユーザーID :<b>${loginUser.employeeId}</b></p>
			</div>
		</div>
	</header>
	<div class="word">
		<h2>今日の励ましの言葉</h2>
		<p>
	    	${motivation}
		</p>
	</div>
	<hr>
	<div class="main">
		<div class="main-menu">
			<h2>📋 メニュー</h2>
			<ul>
		    	<li><a href="event">🎉 イベント</a></li>
		    	
		    	<li><a href="dailyReportPage">📝 日報</a></li>
		    
		    	<li><a href="media">メディア</a></li>
		
		    	<li><a href="EmployeeListServlet">👥 社員一覧</a></li>
		
		    	<li><a href="ChatServlet">💬 Chat</a></li>
		
		    	<li><a href="MyProfileServlet">👤 プロフィール</a></li>
		    
		    	<li><a href="insertExpenses">💰 経費申請</a></li>
		    
		    	<li><a href="insertPaidHoliday">有給申請</a></li>
		    
				<c:if test="${loginUser.employeeId == 1 || loginUser.management == true}">
					<li><a href="admin">⚙ 管理者ページ</a></li>
				</c:if>
			</ul>
		</div>
		<div class="main-news">
			<h2>🔔 お知らせ</h2>
			
			<h3>イベント</h3>
			<c:forEach var="event" items="${eventList}" end="5">
    			<p>
    				🎉 イベント :<b>${event.title}</b><br>
        			📅 ${event.eventDate}
    			</p>
			</c:forEach>

			<h3>日報</h3>
			<c:forEach var="report" items="${reportList}" end="5">
				<p>
					🎉 日報 :<b>${report.title}</b><br>
					著者 : <b>${report.userName}</b><br>
	    			📅 ${report.createdAt}
				</p>
			</c:forEach>
			
			<h3>メディア</h3>
			<c:forEach var="media" items="${mediaList}" end="5">
				<p>
					🎉 日報 :<b>${media.title}</b><br>
					著者 : <b>${media.name}</b><br>
	    			📅 ${media.mediaDate}
				</p>
			</c:forEach>
		</div>
	</div>
	
	<hr>
	<div class="profile">
    	<img src="${empty loginUser.photoPath ? 'images/rdesign_17464.png' : loginUser.photoPath}" width="120" height="120"><br>
    	<a href="MyProfileServlet">プロフィールを見る</a>
	</div>
	<hr>
	
	<footer>
		<a href="LogoutServlet">ログアウト</a>
		<div class="footer-copy">
			<p>&copy; Forz-App</p>
		</div>
	</footer>
</body>
</html>