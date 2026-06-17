<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>グループチャット</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/groupChat.css">
</head>

<body>

<header>
    <div class="header-top">
        <h1><a href="Main">ForzApp</a></h1>
        <div class="header-link">
            <button class="hamburger" onclick="toggleMenu()">☰</button>
			<jsp:include page="/WEB-INF/jsp/header.jsp" />
        </div>
    </div>
</header>

<div class="chat-page">

	<!-- 左：グループ一覧 -->
	<div class="chat-left">

		<h3>👥 グループ一覧</h3>

		<a class="create-btn" href="CreateGroupServlet">＋ グループ作成</a>

		<c:forEach var="g" items="${groupList}">
			<a class="group-item"
			   href="GroupChatServlet?groupId=${g.groupId}">
				${g.groupName}
			</a>
		</c:forEach>

	</div>

	<!-- 右：チャット -->
	<div class="chat-right">

		<c:if test="${group != null}">

			<div class="chat-header">
				<h3>💬 ${group.groupName}</h3>
				<span>${group.createdAt}</span>
			</div>

			<div class="chat-box">

				<c:forEach var="m" items="${msgList}">

					<div class="${m.senderId == loginUser.employeeId ? 'my-message' : 'other-message'}">

						<c:if test="${m.senderId != loginUser.employeeId}">
							<img src="${m.photoPath}" width="30" height="30">
							<b>${m.senderName}</b><br>
						</c:if>

						<c:if test="${m.senderId == loginUser.employeeId}">
							<b>Me</b><br>
						</c:if>

						<c:out value="${m.message}" /><br>

						<span class="time">${m.sendTime}</span>
					</div>

				</c:forEach>

			</div>

			<form class="chat-form" action="GroupChatServlet" method="post">

				<input type="hidden" name="groupId" value="${group.groupId}">
				<textarea name="message" required></textarea>
				<button type="submit">SEND</button>

			</form>

		</c:if>

		<c:if test="${group == null}">
			<p class="empty">左からグループを選択してください</p>
		</c:if>

	</div>

</div>

</body>
</html>