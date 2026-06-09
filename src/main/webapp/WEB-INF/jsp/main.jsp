<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.List" %>
<%@ page import="model.Event" %>
<%@ page import="model.DailyReport" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
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
<hr>
<h2>🔔 Notifications</h2>
<%
List<Event> eventList =(List<Event>)request.getAttribute("eventList");

if(eventList != null){int count = 0;

    for(Event event : eventList){
        if(count >= 3) break;
%>
<p>
🎉 Event :
<b><%= event.getTitle() %></b>
<br>
📅 <%= event.getEventDate() %>
</p>
<hr>
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

        if(count >= 3) break;
%>

<p>

📝 Daily Report :

<b><%= report.getUserName() %></b>

submitted

"<%= report.getTitle() %>"

<br>

📅 <%= report.getCreatedAt() %>

</p>

<hr>

<%
        count++;
    }
}
%>
<hr>
<h2>My Profile</h2>

<img src="images/default.png"width="120"height="120">

<br><br>

<a href="MyProfileServlet">プロフィールを見る</a>
<hr>
<p>© Forz-App</p>
</body>
</html>