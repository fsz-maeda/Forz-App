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

<h1>お知らせ掲示板</h1>

<hr>

<h2>🚨 重要なお知らせ</h2>

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
<h2>🎉 イベントのお知らせ</h2>
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
<h2>🎂 誕生日のお知らせ</h2>
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
<h2>🏆 社員表彰・実績</h2>
<%
for(Notice n : noticeList){if("ACHIEVEMENT".equalsIgnoreCase(n.getCategory())){
%><b><%= n.getTitle() %></b><br><%= n.getContent() %><hr>
<%
    }
}
%><h2>📅 今後の予定</h2><%
for(Notice n : noticeList){if("SCHEDULE".equalsIgnoreCase(n.getCategory())){
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