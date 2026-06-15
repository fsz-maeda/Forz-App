<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Chat</title>

<style>
.chat-box {
	border: 1px solid #ccc;
	padding: 10px;
	height: 400px;
	overflow-y: auto;
}

.my-message {
	text-align: right;
	margin: 10px;
}

.other-message {
	text-align: left;
	margin: 10px;
}

.time {
	color: gray;
	font-size: 12px;
}
</style>

</head>
<body>

	<h2>💬 チャット</h2>

	<h3>ログインユーザー : ${loginUser.name}</h3>

	<hr>

	<form action="ChatServlet" method="get">

		<input type="text" name="keyword" placeholder="社員名検索"> <input
			type="submit" value="検索">

	</form>

	<hr>

	<h3>👥 社員一覧</h3>

	<c:forEach var="emp" items="${employeeList}">

		<c:if test="${emp.employeeId != loginUser.employeeId}">

			<div>

				${emp.name} <a href="ChatServlet?receiverId=${emp.employeeId}">
					チャット </a>

			</div>

			<br>

		</c:if>

	</c:forEach>

	<hr>

	<c:if test="${receiver != null}">

		<h3>👤 Chat With : ${receiver.name}</h3>

		<img src="${receiver.photoPath}" width="100" height="100">

		<br>
		<br>

    社員ID :
    ${receiver.employeeId}

    <hr>

		<h3>📨 Message History</h3>

		<div class="chat-box">

			<c:forEach var="chat" items="${chatList}">

				<c:choose>

					<c:when test="${chat.senderId == loginUser.employeeId}">

						<div class="my-message">

							<b>Me</b> <br>

							<c:out value="${chat.message}" />

							<br> <span class="time"> ${chat.sendTime} </span>

						</div>

					</c:when>

					<c:otherwise>

						<div class="other-message">

							<b> ${receiver.name} </b> <br>

							<c:out value="${chat.message}" />

							<br> <span class="time"> ${chat.sendTime} </span>

						</div>

					</c:otherwise>

				</c:choose>

			</c:forEach>

		</div>

		<hr>

		<form action="ChatServlet" method="post">

			<input type="hidden" name="receiverId" value="${receiver.employeeId}">

			<textarea name="message" rows="5" cols="50" required>
        </textarea>

			<br>
			<br> <input type="submit" value="SEND">

		</form>

	</c:if>

	<c:if test="${receiver == null}">

		<h3>

			<a href="GroupChatServlet"> 👥 グループチャット </a>

		</h3>

	</c:if>

	<hr>

	<a href="Main"> メインへ戻る </a>

</body>
</html>