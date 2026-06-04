<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>コメント投稿</title>
</head>
<body>
<h1>コメント投稿</h1>
<form action="MediaCommentServlet" method="post">
名前<br>
<input type="text" name="name"><br>
コメント<br>
<textarea name="comment" rows="10" cols="30"></textarea><br><br>
<input type="submit" value="投稿する">
</form>
</body>
</html>