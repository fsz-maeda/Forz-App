<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ page import="java.util.List" %>
   <%@ page import="model.Employee" %>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>社員一覧</title>
</head>
<body>
<h2>社員一覧</h2>
<form action="EmployeeListServlet" method="get">
	<input type="text"name="keyword"placeholder="社員名検索">

	<input type="submit"value="Search">
</form>

<hr>
<%
List<Employee> employeeList = (List<Employee>)request.getAttribute("employeeList");
for(Employee emp : employeeList){
%>

<hr>
社員ID: <%=emp.getEmployeeId() %><br>
名前: <%=emp.getName() %><br>
部署 :<%= emp.getDepartment() %><br>
役職 :<%= emp.getPosition() %><br>

<a href="ProfileServlet?employeeId=<%= emp.getEmployeeId() %>">Profile
</a><br>
<a href="ChatServlet?receiverId=<%= emp.getEmployeeId() %>">Chat</a>
<br>
<%
}
%>


</body>
</html>