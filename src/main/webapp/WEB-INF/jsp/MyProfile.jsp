<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Employee" %>
<%
Employee emp = (Employee)request.getAttribute("employee");

if(emp == null){
    out.println("Profile Not Found");
    return;
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Profile</title>
</head>
<body>
<h1>👤 My Profile</h1>
<hr>
<h3>Profile Photo</h3>
<%
String photo = emp.getPhotoPath();

if(photo == null || photo.isEmpty()){
    photo = "images/default.png";
}
%>
<img src="<%= photo %>"width="135"height="150">

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

<h3>📝 自己紹介</h3>
<pre>
<%= emp.getIntro() == null ? "" : emp.getIntro() %>
</pre>
<br>
<hr>

<h3>出勤情報</h3>
<a href="AttendanceServlet">出勤ページ</a>

<hr>

<h3>給料明細</h3>
<a href="expenses">給料ページ</a>

<hr>

<h3>設定</h3>
<a href="${pageContext.request.contextPath}/EditIntroPageServlet">自己紹介編集</a>
<br><br>
<a href="ChangePhotoServlet">プロフィール写真変更</a>

<br><br>

<a href="ChangePasswordServlet"> パスワード変更</a><br><br>

<a href="LogoutServlet">ログアウト</a><br><br>

<a href="Main">🏠 Home</a>

</body>
</html>