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
	<a href="eventAdd"> イベント登録 </a>
	<hr>
	<c:forEach var="event" items="${eventList}">
		<h3>${event.title}</h3>

		<p>${event.content}</p>

		<p>Area : ${event.area}</p>

		<p>Date : ${event.eventDate}</p>

		<hr>
	</c:forEach>

</body>
</html>