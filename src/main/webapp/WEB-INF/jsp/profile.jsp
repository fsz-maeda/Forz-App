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
<h1> 社員プロフィール</h1>

<h2>
    ようこそ、<%= emp.getName() %> さん
</h2>
<hr>
<h3>Profile Photo</h3>
<img src="<%= emp.getPhotoPath() %>"
			width ="150"
			height="150">
<hr>

<h3>📝 自己紹介</h3>

<pre>
<%= emp.getIntro() == null ? "" : emp.getIntro() %>
</pre>

<hr>

社員ID :<%= emp.getEmployeeId() %><br><br>

氏名 :<%= emp.getName() %><br><br>

部署 :<%= emp.getDepartment() %><br><br>

役職 :<%= emp.getPosition() %><br><br>

<a href="Main">🏠 Home</a>

</body>
</html>