<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.Event" %>
<%@ page import="model.Employee" %>
<%@ page import="model.DailyReport" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%
Employee loginUser = (Employee)session.getAttribute("loginUser");

String photo = "images/default.png";

if(loginUser != null &&
   loginUser.getPhotoPath() != null &&
   !loginUser.getPhotoPath().isEmpty()){

    photo = loginUser.getPhotoPath();
}
%>
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
				<p>ユーザネーム :<b><%= loginUser.getName() %></b></p>
				<p>ユーザーID :<b><%= loginUser.getEmployeeId() %></b></p>
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
			<%
			List<Event> eventList =(List<Event>)request.getAttribute("eventList");
		
			if(eventList != null){int count = 0;
		
		    	for(Event event : eventList){
		        	if(count >= 6) break;
			%>
			
			<p>
				🎉 イベント :
				<b><%= event.getTitle() %></b>
				<br>
				📅 <%= event.getEventDate() %>
			</p>
			<%
		        	count++;
		    	}
			}
			%>
		
		
			<%
			List<DailyReport> reportList =
			(List<DailyReport>)request.getAttribute("reportList");
		
			if(reportList != null){
		
		    	int count = 0;
		
		    	for(DailyReport report : reportList){
		
		        	if(count >= 6) break;
			%>
		
			<p>
		
			📝 日報 :
		
			<b><%= report.getUserName() %></b>
		
			submitted
		
			"<%= report.getTitle() %>"
		
			<br>
		
			📅 <%= report.getCreatedAt() %>
		
			</p>
		

		
			<%
		        	count++;
		   		}
			}
			%>
		</div>
	</div>
	
	<hr>
	<div  class="profile">
		<img src="<%=photo%>" width="120" height="120">
		<br><br>
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