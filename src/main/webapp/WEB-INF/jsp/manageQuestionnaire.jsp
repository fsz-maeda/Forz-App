<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>アンケート一覧</title>
</head>
<body>
	<h1>アンケート一覧</h1>
	
	<table>
		<tr>
			<th>アンケートID</th>
			<th>ユーザーID</th>
			<th>アンケート内容</th>
		</tr>
		
		<c:forEach var="question" items="${questionnaireList}">
			<tr>
				<td>${question.questionnaireId}</td>
				<td>${question.employeeId}</td>
				<td>${question.content}</td>
			</tr>
		</c:forEach>
	</table>
	
	<a href="admin">戻る</a>
</body>
</html>