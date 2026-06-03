<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Media,java.util.List"%>
<%
List<Media> mediaList = (List<Media>)session.getAttribute("mediaList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>media</title>
</head>
<body>
<h1>media</h1>
<table border="1">
<tr>
<th>カテゴリ</th>
<th>投稿日時</th>
<th>タイトル</th>
<th>内容</th>
</tr>
<%for (Media media : mediaList){%>
<td><%= media.getMediaType()%></td><td><%= media.getMediaDate()%></td><td><%= media.getTitle()%></td><td><%= media.getContent()%></td><tr>
<%} %>
</table><br>
<a href="MediaPostServlet">新規投稿</a><br>
<a href="Main">メイン画面へ</a>
</body>
</html>