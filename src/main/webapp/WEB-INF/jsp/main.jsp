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
			<h1>ForzApp</h1>
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
		    	<li><a href="event">🎉 社内イベント</a></li>
		    	
		    	<li><a href="dailyReportPage">📝 日報</a></li>
		    
		    	<li><a href="media">🏢 部署コミュニティ</a></li>
		
		    	<li><a href="EmployeeListServlet">👥 社員一覧</a></li>
		
		    	<li><a href="ChatServlet">💬 Chat</a></li>
		
		    	<li><a href="MyProfileServlet">👤 プロフィール</a></li>
		    
		    	<li><a href="application">📃 各種申請</a></li>
		    	
		    	<li><a href="salary">💰 給料ページ</a></li>
		    
				<c:if test="${loginUser.employeeId == 1 || loginUser.management == true}">
					<li><a href="admin">⚙ 管理者ページ</a></li>
				</c:if>
				<li><a href="LogoutServlet">ログアウト</a></li>
			</ul>
		</div>

		<div class="main-news">

		
			<div>
			<h3>イベント</h3>
				<c:forEach var="event" items="${eventList}" end="5">
					<a href="detailEvent?eventId=${event.eventId}">
					<div class="news-infomation">
		    			<p>
		    				🎉 イベント :<b>${event.title}</b><br>
		    				<c:forEach var="employee" items="${employeeList }">
			    				<c:if test="${event.employeeId == employee.employeeId }">
			    					👤 著者 : <b>${employee.name }</b><br>
			    				</c:if>
		    				</c:forEach>
		        			📅 ${event.eventDate}
		    			</p>
	    			</div>
	    			</a>
				</c:forEach>
			</div>
			
			<div>
				<h3>日報</h3>
				<c:forEach var="report" items="${reportList}" end="5">
					<a href="detailDailyReport?dailiReportId=${report.dailyReportId}">
					<div class="news-infomation">
						<p>
							🎉 ${report.reportType} :<b>${report.title}</b><br>
							👤 著者 : <b>${report.userName}</b><br>
			    			📅 ${report.createdAt}
						</p>
						</div>
					</a>
				</c:forEach>
			</div>
			
			<div>
			<h3>部署コミュニティ</h3>
				<c:forEach var="media" items="${mediaList}" end="5">
					<a href="ArticleContentServlet?id=${media.ID}">
					<c:if test="${media.departmentId == loginUser.department}">
						<div class="news-infomation">
							<p>
								🎉 部署情報 :<b>${media.title}</b><br>
								👤 著者 : <b>${media.name}</b><br>
				    			📅 ${media.createdAt}
							</p>
						</div>
					</c:if>
					</a>
				</c:forEach>
			</div>
			
		</div>
	</div>
	
	<hr>
	<div class="profile">
    	<img src="${empty loginUser.photoPath ? 'images/rdesign_17464.png' : loginUser.photoPath}" width="120" height="120"><br>
    	<a href="MyProfileServlet">プロフィールを見る</a>
	</div>
	<hr>
	

</body>
</html>