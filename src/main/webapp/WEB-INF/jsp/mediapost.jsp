<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="model.User"%>
 <%
 String s = (String)session.getAttribute("errorMsg");
 String s2 = (String)session.getAttribute("errorMsg2");
 String s3 = (String)session.getAttribute("errorMsg3");
 User loginUser = (User)session.getAttribute("loginUser");
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規投稿</title>
</head>
<body>
<h1>新規投稿</h1>
<form action="MediaPostServlet" method="post">
カテゴリー<br>
<select name="category">
  <option value="news">ニュース</option>
  <option value="notice">お知らせ</option>
  <option value="others">その他</option>
 </select><br>
タイトル<br>
<input type="text" name="title"><br>
<%= s2%><br>
内容<br>
<textarea name="content" rows="10" cols="30"></textarea><br>
<%= s3%><br><br>
<input type="hidden" name="departmentId"value="<%= loginUser.getDepartmentId()%>">
<input type="submit" value="投稿する">
</form><br>
<a href="media">メディアページへ</a>
</body>
</html>