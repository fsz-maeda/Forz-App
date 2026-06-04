<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="model.Employee" %>
  <%
  Employee emp = (Employee)request.getAttribute("employee");
  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Profile</title>
</head>
<body>
<h1>👤 Profile</h1>
<hr>
<h3>Profile Photo</h3>
<img src="<%= emp.getPhotoPath() %>"
			width ="150"
			height="150">
<hr>
社員ID :
<%= emp.getEmployeeId() %><br><br>

氏名 :
<%= emp.getName() %><br><br>

部署 :
<%= emp.getDepartment() %><br><br>

役職 :
<%= emp.getPosition() %><br><br>

<hr>
<h3>出勤情報</h3>
<a href="#">出勤ページ</a>
<hr>
<h3>給料明細</h3>
<a href="#">給料ページ</a>
<hr>
<h3>設定</h3>
<a href="#">パスワード変更</a><br>
<a href="#">プロフィール写真変更</a><br><br>

<a href="Main">🏠 Home</a>



</body>
</html>