<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Media"%>
<%
Media media = (Media)session.getAttribute("media");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>記事</title>
</head>
<body>
<h1><%= media.getTitle()%></h1><br>
<hr><br>
<%= media.getContent()%>
<footer><a href="media">メディア画面へ</a>
<a href="MediaCommentServlet">コメントを書く</a>
</footer>
</body>
</html>