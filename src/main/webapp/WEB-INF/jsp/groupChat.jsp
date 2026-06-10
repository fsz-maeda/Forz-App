<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="model.Group" %>
<%@ page import="model.GroupMessage" %>
<%@ page import="model.Employee" %>

<%
Employee loginUser = (Employee)session.getAttribute("loginUser");
Group group = (Group)request.getAttribute("group");
List<GroupMessage> msgList =
        (List<GroupMessage>)request.getAttribute("msgList");
List<Group> groupList =
        (List<Group>)request.getAttribute("groupList");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Group Chat</title>
</head>
<body>

<h2>👥 Groups</h2>

<%
if(groupList != null){
    for(Group g : groupList){
%>

<div>
    <a href="GroupChatServlet?groupId=<%= g.getGroupId() %>">
        <%= g.getGroupName() %>
    </a>
</div>

<%
    }
}
%>

<hr>

<a href="GroupChatServlet?groupId=1">
    💬 Chat Area
</a>

<%
if(group == null){
%>

<%
}else{
%>

<h3><%= group.getGroupName() %></h3>

<hr>

<%
if(msgList != null && !msgList.isEmpty()){

    for(GroupMessage m : msgList){

        if(loginUser != null &&
           m.getSenderId() == loginUser.getEmployeeId()){
%>

<div>
    <b>Me:</b>
    <%= m.getMessage() %>
</div>

<%
        }else{
%>

<div>

<img src="<%= m.getPhotoPath() %>"
     width="35"
     height="40">

<b>
<%=(m.getSenderId() == loginUser.getEmployeeId())
? "Me"
 : m.getSenderName() %>
</b>

:
<%= m.getMessage() %>

</div>

<br>

<%
        }
    }

}else{
%>

<div>No messages yet 💬</div>

<%
}
%>

<hr>

<form action="GroupChatServlet" method="post">

    <input type="hidden"
           name="groupId"
           value="<%= group.getGroupId() %>">

    <textarea name="message"
              rows="4"
              cols="40"></textarea>

    <br><br>

    <input type="submit"
           value="SEND">

</form>

<br>

<a href="CreateGroupServlet">
    ➕ Create New Group
</a>

<%
}
%>

<hr>

<a href="ChatServlet">Back</a>

</body>
</html>