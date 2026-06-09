<%@ page contentType="text/html; charset=UTF-8" %>

<html>
<head>
<title>Create Group</title>
</head>
<body>

<h2>Create New Group</h2>

<form action="CreateGroupServlet" method="post">

    Group Name:
    <input type="text" name="groupName">

    <br><br>

    <input type="submit" value="Create">

</form>

<br>

<a href="GroupChatServlet">Back</a>

</body>
</html>