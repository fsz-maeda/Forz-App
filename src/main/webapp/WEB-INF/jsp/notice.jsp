<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>お知らせ</title>
</head>
<body>
<h2>お知らせ一覧</h2>
<%
List<Notice>noticeList = (List<Notice>)
request.getAttribute("noticeList");
for(Notice n: noticeList){
%>	
<hr>
<h3><%=n.getTitle() %></h3>
<p>カテゴリー:<%= n.getCategory() %></p>
<p><%= n.getContent() %></p>

<% } %>


</body>
</html>