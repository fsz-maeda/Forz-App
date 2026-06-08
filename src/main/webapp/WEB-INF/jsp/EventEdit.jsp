<%@ page contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>イベント編集</title>

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

.form-box {
	background-color: white;
	padding: 20px;
	border-radius: 10px;
	box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
	max-width: 700px;
	margin: auto;
}

input[type="text"], input[type="date"], textarea {
	width: 100%;
	padding: 10px;
	margin-top: 5px;
	margin-bottom: 15px;
	box-sizing: border-box;
}

textarea {
	height: 150px;
	resize: vertical;
}

input[type="submit"] {
	background-color: #005bac;
	color: white;
	border: none;
	padding: 10px 20px;
	border-radius: 5px;
	cursor: pointer;
}

input[type="submit"]:hover {
	background-color: #003f7f;
}

.back-link {
	display: inline-block;
	margin-top: 20px;
	color: #005bac;
	font-weight: bold;
	text-decoration: none;
}
</style>

</head>
<body>

	<h1>イベント編集</h1>

	<div class="form-box">

		<form action="eventEdit" method="post">

			<input type="hidden" name="eventId" value="${event.eventId}">

			タイトル<br> <input type="text" name="title" value="${event.title}"
				required> 内容<br>

			<textarea name="content" required>${event.content}</textarea>

			エリア<br> <input type="text" name="area" value="${event.area}"
				required> 開催日<br> <input type="date" name="eventDate"
				value="${event.eventDate}" required> <br> <input
				type="submit" value="更新">

		</form>

		<br> <a class="back-link" href="event"> イベント一覧へ戻る </a>

	</div>

</body>
</html>