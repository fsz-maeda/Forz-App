<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
String errorMsg = (String)request.getAttribute("errorMsg");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>パスワード変更</title>
</head>
<body>

<h2>パスワード変更</h2>

<hr>

<%
if(errorMsg != null){
%>

<p style="color:red;">
<%= errorMsg %>
</p>

<hr>

<%
}
%>

<form action="${pageContext.request.contextPath}/UpdatePasswordServlet"
      method="post">

    <h3>現在のパスワード</h3>

    <input type="password"
           name="oldPass"
           required>

    <br><br>

    <h3>新しいパスワード</h3>

    <input type="password"
           name="newPass"
           required>

    <br><br>

    <h3>新しいパスワード（確認）</h3>

    <input type="password"
           name="confirmPass"
           required>

    <br><br>

    <input type="submit"
           value="🔄 パスワード変更">

</form>

<hr>

<a href="MyProfileServlet">
    👤 My Profile に戻る
</a>

<br><br>

<a href="Main">
    🏠 Home
</a>

</body>
</html>