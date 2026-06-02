<%@ page contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>イベント登録</title>
</head>
<body>

	<h1>イベント登録</h1>

	<form action="eventAdd" method="post">

		タイトル<br> <input type="text" name="title"><br>
		<br> 内容<br>
		<textarea name="content"></textarea>
		<br>
		<br> エリア<br> <input type="text" name="area"><br>
		<br> 開催日<br> <input type="date" name="eventDate"><br>
		<br> <input type="submit" value="登録">

	</form>

	<hr>

	<a href="${pageContext.request.contextPath}/event"> イベント一覧へ戻る </a>

</body>
</html>