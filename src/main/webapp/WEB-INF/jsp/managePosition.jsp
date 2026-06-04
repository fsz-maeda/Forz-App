<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>役職管理</title>
</head>
<body>
	<h1>役職管理</h1>	
		<h2>新規役職追加</h2>
		
		<form action="insertPosition" method="post">
			<input type="submit" value="追加">
		</form>
		
		<h2>既存役職変更</h2>
		<table>
			<tr>
				<th>役職ID</th>
				<th>役職名</th>
				<th>修正</th>
				<th>削除</th>
			</tr>
			
			<c:forEach var="position" items="${positionList}">
				<tr>
					<td>${position.positionId}</td>
					<td>${position.positionName}</td>
					
					<td>
						<form action="updatePosition" method="post">
							<input type="hidden" name="positionId" value="${position.positionId}">
							<input type="submit" value="修正">
						</form>
					</td>
					
					<td>
						<form action="deletePosition" method="post" onsubmit="return confirm('削除しますか？')">
							<input type="hidden" name="positionId" value="${position.positionId}">
							<input type="submit" value="削除">
						</form>
					</td>
				</tr>
			</c:forEach>
		</table>
		
		<c:if test="${insertPositionMsg != null}">
			${insertPositionMsg}
			<c:remove  var="insertPositionMsg" scope="session"/>
		</c:if><br>
		
		<c:if test="${updatePositionMsg != null}">
			${updatePositionMsg}
			<c:remove  var="updatePositionMsg" scope="session"/>
		</c:if><br>
		
		<c:if test="${deletePositionMsg != null}">
			${deletePositionMsg}
			<c:remove  var="deletePositionMsg" scope="session"/>
		</c:if><br>
		
		<a href="admin">戻る</a>
</body>
</html>