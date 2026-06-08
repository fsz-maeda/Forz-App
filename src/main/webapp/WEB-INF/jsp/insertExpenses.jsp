<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>経費申請</title>
</head>
<body>
	<h1>経費申請</h1>
	<form action="insertExpenses" method="post">
		申請額<input type="number" name="amount">
		詳細<input type="text" name="detail">
		<input type="submit" value="申請">
	</form>
</body>
</html>