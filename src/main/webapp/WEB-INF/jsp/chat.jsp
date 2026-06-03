<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="model.Employee" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Chat</title>
</head>

<body>

<h2>Chat</h2>
<form action="ChatServlet" method="get">
    <input type="text" name="keyword" placeholder="社員名検索">
    <input type="submit" value="Search">
</form><hr>

<%
List<Employee> employeeList =
(List<Employee>) request.getAttribute("employeeList");

if(employeeList == null){ employeeList = new java.util.ArrayList<>();
}

String receiverId = (String) request.getAttribute("receiverId");
if(receiverId == null){
    receiverId = "";
}
%>
<%
for (Employee emp : employeeList) {
%>

<div style="margin-bottom:10px;">
    社員名: <%= emp.getName() %>
    <a href="ChatServlet?receiverId=<%= emp.getEmployeeId() %>">Select</a>
</div>

<%
}
%>

<hr>
<form action="ChatServlet" method="post">

    <input type="hidden" name="receiverId" value="<%= receiverId %>">

    <label>Message</label><br>

    <textarea name="message" rows="5" cols="40"></textarea>

    <br><br>

    <input type="submit" value="SEND">

</form>

</body>
</html>