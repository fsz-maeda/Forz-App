<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー管理</title>
</head>
<body>
	<h1>ユーザー管理ページ</h1>
		<table>
			<tr>
				<th>ID</th>
				<th>名前</th>
				<th>メールアドレス</th>
				<th>ポジション名</th>
				<th>修正</th>
				<th>削除</th>
			</tr>
			
			<c:forEach var="user" items="${userPositionList }">
				<tr>
					<td>${user.userId}</td>
					<td>${user.name }</td>
					<td>${user.mail }</td>
					<td>${user.positionName }</td>
					
					<td>
						<form action="editUser" method="post">
							<input type="hidden" name="userId" value="${user.userId}">
							<input type="submit" value="修正">
						</form>
					</td>
						
					<td>
						<form action="deleteUser" method="post" onsubmit="return confirm('削除しますか？')">
							<input type="hidden" name="userId" value="${user.userId}">
							<input type="submit" value="削除">
						</form>
					</td>
				<tr>
			</c:forEach>
		</table>
		
		<c:if test="${updateUserMsg != null}">
			${updateUserMsg }
		</c:if>
		
		<c:if test="${deleteUserMsg != null}">
			${deleteUserMsg }
		</c:if>
		
		<a href="admin">戻る</a>
</body>
</html>