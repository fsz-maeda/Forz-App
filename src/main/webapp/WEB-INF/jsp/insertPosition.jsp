<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>役職入力</title>
</head>
<body>
	<h1>役職入力</h1>
	
	<form action="insertPositionCheck" method="post">
		役職ID<input type="number" name="positionId"><br>
		役職名<input type="text" name="positionName"><br>
		<input type="submit" value="入力">
	</form>
	
	<a href="managePosition">戻る</a>
</body>
</html>