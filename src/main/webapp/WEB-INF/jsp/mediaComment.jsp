<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.MediaComment"%>
<%@ page import="model.Media" %>
 <%
 String s = (String)session.getAttribute("esg1");
 String s2 = (String)session.getAttribute("esg2");
 Media media = (Media)session.getAttribute("media");
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>コメント投稿</title>
</head>
<body>
<h1>コメント投稿</h1>
</head>
<form action="MediaCommentServlet" method="post">
コメント<br>
<textarea name="comment" rows="10" cols="30"><%= request.getParameter("comment") != null ? request.getParameter("comment") : "" %></textarea><br>
<%= s2%><br>
<input type="hidden" name ="IID" value="<%= media.getId() %>">
<input type="submit" value="投稿する">
</form>
<form action="ArticleContentServlet"method="post">
<input type="submit" value="戻る">
</form>
</body>
</html>