<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Event Page</title>
</head>
<body>

	<h1>All Event Show</h1>
	
	<a href="eventAdd">イベント登録</a>

	<hr>

	<c:forEach var="event" items="${eventList}">

		<h3>${event.title}</h3>

		<p>${event.content}</p>

		<p>Area : ${event.area}</p>

		<p>Date : ${event.eventDate}</p>
		
		
		<p>いいね数：${event.likes}</p>


		<!-- いいねボタン -->
		<form action="like" method="post">
			<input type="hidden" name="eventId" value="${event.eventId}">
			<input type="submit" value="いいね">
		</form>

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

		<hr>

	</c:forEach>

</body>
</html>