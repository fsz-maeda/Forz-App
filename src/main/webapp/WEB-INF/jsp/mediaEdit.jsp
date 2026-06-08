<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.Media" %>
<%
Media editMedia = (Media) request.getAttribute("editMedia");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>記事の編集</title>
</head>
<body>
<h1>記事の編集</h1>
<hr><br>

<form action="MediaEditServlet" method="post">
    
    <input type="hidden" name="id" value="<%= editMedia.getId() %>">

    <label for="title">タイトル：</label><br>
    <input type="text" id="title" name="title" value="<%= editMedia.getTitle() %>" style="width:300px;"><br><br>

    <label for="content">本文：</label><br>
    <textarea id="content" name="content" rows="10" cols="50"><%= editMedia.getContent() %></textarea><br><br>

    <button type="submit">更新する</button>
    <a href="media">キャンセルして一覧へ</a>
</form>

</body>
</html>
