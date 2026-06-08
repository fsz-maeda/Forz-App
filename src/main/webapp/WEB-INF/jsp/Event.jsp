<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Event Page</title>
<style>
body {
	background-color: #f4f6f9;
	font-family: Arial, sans-serif;
	margin: 30px;
}

h1 {
	background-color: #005bac;
	color: white;
	padding: 15px;
	border-radius: 10px;
	text-align: center;
}

a {
	color: #005bac;
	font-weight: bold;
	text-decoration: none;
}

a:hover {
	text-decoration: underline;
}

.event-card {
	background-color: white;
	border-left: 8px solid #005bac;
	padding: 15px;
	margin-bottom: 20px;
	border-radius: 10px;
	box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
}

input[type="submit"] {
	background-color: #005bac;
	color: white;
	border: none;
	padding: 8px 15px;
	border-radius: 5px;
	cursor: pointer;
}

input[type="submit"]:hover {
	background-color: #003f7f;
}

input[type="text"] {
	padding: 5px;
	width: 250px;
}

.page-link {
	margin: 5px;
	padding: 8px 12px;
	background-color: #005bac;
	color: white;
	border-radius: 5px;
}
</style>
</head>
<h1>All Event Show</h1>

<a href="eventAdd">イベント登録</a>
<a href="Main">メインに戻る</a>
<hr>

<c:forEach var="event" items="${eventList}">
	<div class="event-card">
		<h3>${event.title}</h3>

		<p>${event.content}</p>

		<p>Area : ${event.area}</p>

		<p>Date : ${event.eventDate}</p>

		<p>いいね数：${event.likes}</p>

		<c:choose>

			<c:when test="${event.liked}">
				<form action="like" method="post">
					<input type="hidden" name="eventId" value="${event.eventId}">
					<input type="submit" value="いいね解除">
				</form>
			</c:when>

			<c:otherwise>
				<form action="like" method="post">
					<input type="hidden" name="eventId" value="${event.eventId}">
					<input type="submit" value="いいね">
				</form>
			</c:otherwise>

		</c:choose>

		<br>

		<!-- コメント投稿 -->
		<form action="commentAdd" method="post">

			<input type="hidden" name="eventId" value="${event.eventId}">

			<input type="text" name="comment" placeholder="コメントを入力"> <input
				type="submit" value="コメント">

		</form>

		<!-- コメント一覧 -->
		<h4>コメント一覧</h4>

		<c:forEach var="comment" items="${event.commentList}">

			<p>${comment.comment}</p>

		</c:forEach>
		<c:if test="${loginUser.employeeId == event.userId}">
			<form action="eventDelete" method="post"
				onsubmit="return confirm('このイベントを削除しますか？');">

				<input type="hidden" name="eventId" value="${event.eventId}">

				<input type="submit" value="削除">

			</form>
		</c:if>
	</div>
</c:forEach>

<!-- ページネーション -->

<c:forEach var="i" begin="1" end="${totalPages}">

	<a class="page-link" href="event?page=${i}"> ${i} </a>
</c:forEach>
</body>
</html>