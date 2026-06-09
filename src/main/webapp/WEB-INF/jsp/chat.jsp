<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="model.Employee" %>
<%@ page import="model.Chat" %>
<%@ page import="model.Group" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Chat</title>
</head>
<body>
<h2>💬 Chat System</h2>
<%Employee loginUser =(Employee)session.getAttribute("loginUser");%>
<h3>Login User :<%= loginUser.getName() %></h3>

<hr>
<form action="ChatServlet" method="get">
    <input type="text"name="keyword"placeholder="社員名検索">

    <input type="submit"value="Search">
</form>

<hr>

<%
List<Employee> employeeList =(List<Employee>)request.getAttribute("employeeList");

if(employeeList == null){employeeList = new java.util.ArrayList<>();
}

String receiverId =(String)request.getAttribute("receiverId");

if(receiverId == null){
    receiverId = "";
}

Employee receiver =(Employee)request.getAttribute("receiver");

List<Chat> chatList =(List<Chat>)request.getAttribute("chatList");
%>

<h3>👥 社員一覧</h3>
<%for(Employee emp : employeeList){%>

<div>
    <%= emp.getName() %>

    <a href="ChatServlet?receiverId=<%= emp.getEmployeeId() %>">
        Chat
    </a>
</div>

<br>
<%
}
%>

<hr>

<% if(receiver != null){ %>

<h3>👤 Chat With :<%= receiver.getName() %></h3>

<img src="<%= receiver.getPhotoPath() %>" width="100"height="100">

<br><br>

社員ID :<%= receiver.getEmployeeId() %>

<hr>

<h3>📨 Message History</h3>

<%
if(chatList != null){

    for(Chat chat : chatList){

        if(chat.getSenderId()
                == loginUser.getEmployeeId()){
%>

<div>

<b>Me :</b>

<%= chat.getMessage() %>

</div>

<br>

<%
        }else{
%>

<div>

<b>
<%= receiver.getName() %> :
</b>

<%= chat.getMessage() %>

</div>

<br>

<%
        }
    }
}
%>

<hr>

<form action="ChatServlet"
      method="post">

    <input type="hidden"name="receiverId"value="<%= receiverId %>">

    <label>Message</label>

    <br><br>

    <textarea name="message"rows="5"cols="50"></textarea><br><br>

    <input type="submit"value="SEND">

</form>

<% } else { %>
<h3>
    <a href="GroupChatServlet">Group Chat</a>
</h3>

<% } %>

<hr>

<a href="Main">🏠 Home</a>

</body>
</html>