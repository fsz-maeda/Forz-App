<%@ page language="java"
contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="model.Notice" %>

<%
List<Notice> noticeList =
(List<Notice>)request.getAttribute("noticeList");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>お知らせ</title>
</head>
<body>

<h1>📢 Company Notice Board</h1>

<hr>

<h2>🚨 Important Notice</h2>

<%
for(Notice n : noticeList){if("IMPORTANT".equalsIgnoreCase(n.getCategory())){
%>

<b><%= n.getTitle() %></b><br>
<%= n.getContent() %>

<hr>
<%
    }
}
%>
<h2>🎉 Event Notice</h2>
<%
for(Notice n : noticeList){
    if("EVENT".equalsIgnoreCase(n.getCategory())){
%>
<b><%= n.getTitle() %></b><br>
<%= n.getContent() %>
<hr>

<%
    }
}
%>
<h2>🎂 Birthday Notice</h2>
<%
for(Notice n : noticeList){
    if("BIRTHDAY".equalsIgnoreCase(n.getCategory())){
%>
<b><%= n.getTitle() %></b><br>
<%= n.getContent() %>
<hr>
<%
    }
}
%>
<h2>🏆 Employee Achievement</h2>
<%
for(Notice n : noticeList){if("ACHIEVEMENT".equalsIgnoreCase(n.getCategory())){
%><b><%= n.getTitle() %></b><br><%= n.getContent() %><hr>
<%
    }
}
%><h2>📅 Upcoming Schedule</h2><%
for(Notice n : noticeList){if("SCHEDULE".equalsIgnoreCase(n.getCategory())){
%>

<b><%= n.getTitle() %></b><br>
<%= n.getContent() %>
<hr>

<%
    }
}
%>
<h2>🏢 HR Notice</h2>
<%
for(Notice n : noticeList){
    if("HR".equalsIgnoreCase(n.getCategory())){
%>

<b><%= n.getTitle() %></b><br>
<%= n.getContent() %>

<hr>

<%
    }
}
%>

<h2>💻 IT / System Notice</h2>

<%
for(Notice n : noticeList){
    if("SYSTEM".equalsIgnoreCase(n.getCategory())){
%>

<b><%= n.getTitle() %></b><br>
<%= n.getContent() %>

<hr>

<%
    }
}
%>

<h2>📢 General Notice</h2>

<%
for(Notice n : noticeList){
    if("GENERAL".equalsIgnoreCase(n.getCategory())){
%>

<b><%= n.getTitle() %></b><br>
<%= n.getContent() %>

<hr>

<%
    }
}
%>

<br>

<a href="Main">🏠 Home</a>

</body>
</html>