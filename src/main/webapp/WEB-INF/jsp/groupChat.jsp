<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>グループチャット</title>

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

.group-list {
	margin-bottom: 20px;
}
</style>

</head>
<body>

	<h2>👥 グループチャット</h2>

	<p>
		ログインユーザー : <b>${loginUser.name}</b>
	</p>

	<a href="CreateGroupServlet"> ➕ グループ作成 </a>

	<hr>

	<div class="group-list">

		<h3>📁 グループ一覧</h3>

		<c:forEach var="g" items="${groupList}">

			<div>

				<a href="GroupChatServlet?groupId=${g.groupId}"> ${g.groupName}
				</a>

			</div>

			<br>

		</c:forEach>

	</div>

	<hr>

	<c:if test="${group != null}">

		<h3>💬 ${group.groupName}</h3>

		<p>作成日時 : ${group.createdAt}</p>

		<hr>

		<div class="chat-box">

			<c:choose>

				<c:when test="${not empty msgList}">

					<c:forEach var="m" items="${msgList}">

						<c:choose>

							<c:when test="${m.senderId == loginUser.employeeId}">

								<div class="my-message">

									<b>Me</b> <br>

									<c:out value="${m.message}" />

									<br> <span class="time"> ${m.sendTime} </span>

								</div>

							</c:when>

							<c:otherwise>

								<div class="other-message">

									<img src="${m.photoPath}" width="35" height="40"> <b>
										${m.senderName} </b> <br>

									<c:out value="${m.message}" />

									<br> <span class="time"> ${m.sendTime} </span>

								</div>

							</c:otherwise>

						</c:choose>

					</c:forEach>

				</c:when>

				<c:otherwise>

					<div>No messages yet 💬</div>

				</c:otherwise>

			</c:choose>

		</div>

		<hr>

		<form action="GroupChatServlet" method="post">

			<input type="hidden" name="groupId" value="${group.groupId}">

			<textarea name="message" rows="5" cols="50" required></textarea>

			<br>
			<br> <input type="submit" value="SEND">

		</form>

	</c:if>

	<hr>

	<a href="ChatServlet"> ← 個人チャットへ戻る </a>

</body>
</html>