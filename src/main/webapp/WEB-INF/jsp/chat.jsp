<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Chat</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/chat.css">

</head>

<body>

<header>
	<div class="header-top">
		<h1><a href="Main">ForzApp</a></h1>
		<div class="header-link">
			<a href="Main">🏠 メインへ</a>
		</div>
	</div>
</header>

<div class="chat-layout">

	<!-- 左：ユーザー一覧 -->
	<div class="user-panel">

		<h3>👥 社員一覧</h3>

		<form action="ChatServlet" method="get">
			<input type="text" name="keyword" placeholder="社員検索">
			<button type="submit">検索</button>
		</form>

		<div class="user-list">

			<c:forEach var="emp" items="${employeeList}">
				<c:if test="${emp.employeeId != loginUser.employeeId}">
					<a class="user-card" href="ChatServlet?receiverId=${emp.employeeId}">
						${emp.name}
					</a>
				</c:if>
			</c:forEach>

		</div>

		<hr>

		<a href="GroupChatServlet">👥 グループチャット</a>

	</div>

	<!-- 右：チャット本体 -->
	<div class="chat-panel">

		<c:if test="${receiver != null}">

			<div class="chat-header">
				<h3>💬 ${receiver.name}</h3>
				<span>ID: ${receiver.employeeId}</span>
			</div>

			<div class="chat-box">

				<c:forEach var="chat" items="${chatList}">

					<c:choose>

						<c:when test="${chat.senderId == loginUser.employeeId}">
							<div class="msg my">
								<div class="bubble">
									${chat.message}
								</div>
								<span>${chat.sendTime}</span>
							</div>
						</c:when>

						<c:otherwise>
							<div class="msg other">
								<div class="bubble">
									${chat.message}
								</div>
								<span>${chat.sendTime}</span>
							</div>
						</c:otherwise>

					</c:choose>

				</c:forEach>

			</div>

			<form action="ChatServlet" method="post" class="chat-form">

				<input type="hidden" name="receiverId" value="${receiver.employeeId}">

				<textarea name="message" placeholder="メッセージ入力..." required></textarea>

				<button type="submit">送信</button>

			</form>

		</c:if>

		<c:if test="${receiver == null}">
			<div class="empty-chat">
				<h3>👈 ユーザーを選択してください</h3>
			</div>
		</c:if>

	</div>

</div>

</body>
</html>