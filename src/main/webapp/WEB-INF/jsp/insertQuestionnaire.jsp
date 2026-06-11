<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>アンケート</title>
</head>
<body>
	<h1>アンケート記入</h1>
	
	<form action="insertQuestionnaireCheck" method="post">
		匿名希望<input type="radio" name="employeeId" value="">
		アンケート内容
		<textarea id="message" name="content" rows="5" cols="40" 
		placeholder="ご自由にお書きください"></textarea>
	</form>
	
	<table>
		<tr>
			<th>アンケートID</th>
			<th>アンケート内容</th>
		</tr>
		
		<c:forEach var="question" items="questionnaireList">
			<tr>
				<td>${question.questionnaireId}</td>
				<td>${question.content}</td>
			</tr>
		</c:forEach>
	</table>
	
	<c:if test="${insertQuestionnaire != null}">
		${insertQuestionnaireMsg}
		<c:remove var="insertQuestionnaire" scope="session"/>
	</c:if>
</body>
</html>