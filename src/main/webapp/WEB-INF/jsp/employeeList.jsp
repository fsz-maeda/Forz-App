<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ page import="java.util.List" %>
   <%@ page import="model.Employe" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>社員一覧</title>
</head>
<body>
<h2>社員一覧</h2>
<%
List<Employee> employeeList = (List<Employee>)request.getAttribute("employeeList");
for(Employee emp : employeeList){
%>

<hr>
社員ID: <%=emp.getEmployeeId() %><br>
名前: <%=emp.getName() %><br>
部署 :<%= emp.getDepartment() %><br>
役職 :<%= emp.getPosition() %><br>
写真 :<%=emp.getPhotoPath() %><br><br>

<a href="#">Profile</a>
<a href="#">Chat</a>
<br>
<%
}
%>


</body>
</html>