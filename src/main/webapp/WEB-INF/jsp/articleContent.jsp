<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Media"%>
<%@ page import="model.MediaComment"%>
<%@ page import="model.Employee"%>
<%@ page import="java.util.List" %>
<%
Media media = (Media)session.getAttribute("media");
List<MediaComment> commentlist = (List<MediaComment>)session.getAttribute("commentlist");
Employee loginUser = (Employee)session.getAttribute("loginUser");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>記事</title>
</head>
<body>
<h1><%= media.getTitle()%></h1><br>
<% if (loginUser != null && loginUser.getEmployeeId() == media.getUserId()) { %>
    <form action="MediaEditServlet" method="get" style="display:inline;">
        <input type="hidden" name="id" value="<%= media.getId() %>">
        <button type="submit">この記事を編集する</button>
    </form>
    <form action="MediaDeleteServlet" method="post" style="display:inline;" onsubmit="return confirm('本当にこの記事を削除しますか？');">
        <input type="hidden" name="id" value="<%= media.getId() %>">
        <button type="submit" style="color: red;">この記事を削除する</button>
    </form>
<% } %>
    <br><br>
<hr><br>
<%= media.getContent()%><br>
<footer><a href="media">メディア画面へ</a>
<a href="MediaCommentServlet">コメントを書く</a>
</footer><br>
<h2>コメント一覧</h2>
<table border="1"style="border-collapse: collapse">
<tr>
<th>名前</th>
<th>コメント</th>
</tr>
<% for (MediaComment mc : commentlist){ %>
<td><%= loginUser.getName()%></td><td><%= mc.getComment()%></td><tr>
<%} %>
</table>
</body>
</html>