<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>グループチャット</title>
</head>
<body>
	<h2>👥 グループ</h2>
	
	<a href="CreateGroupServlet"> ➕ グループ作成</a>
	
	<hr>
	
	<c:if test="${groupList != null}">
		<c:forEach var="g" items="${groupList}">
			<div>
	    		<a href="GroupChatsServlet?groupId=${g.groupId}">
	       		 	${g.groupName}
	       		 </a>
			</div>
		</c:forEach>
	</c:if>
	
	<hr>
	
	<a href="GroupChatServlet?groupId=1">
	    💬 チャット
	</a>
	
	<c:if test="${group != null}">
		<h3>${group.groupName}</h3>
		
		<hr>
		
		<c:choose>
			<c:when test="${msgList != null && !msgList.isEmpty()}">
				<c:forEach var="m" items="${msgList}">
				    <c:choose>
				        <c:when test="${m.senderId == loginUser.employeeId}">
				            <div>
				                <b>Me:</b>
				                ${m.message}
				            </div>
				        </c:when>
				        <c:otherwise>
				            <div>
				                <img src="${m.photoPath}" width="35" height="40">
				
				                <b>${m.senderName}</b>
				                : ${m.message}
				            </div>
				        </c:otherwise>
				    </c:choose>
				    <br>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<div>No messages yet 💬</div>
			</c:otherwise>
		</c:choose>
		
		<hr>
		
		<form action="GroupChatServlet" method="post">
	    	<input type="hidden" name="groupId"value="${group.getGroupId}">
	    	<textarea name="message" rows="4" cols="40"></textarea>
	    	
	    	<br>
	    	<br>
	
	    	<input type="submit"  value="SEND">
		</form>
	</c:if>
	
	<hr>
	
	<a href="ChatServlet">戻る</a>
	
	</body>
</html>