<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>部署管理</title>
</head>
<body>
	<h1>部署管理</h1>
	
	<h2>新規部署追加</h2>
	
		<form action="insertDepartment" method="post">
			<input type="submit" value="追加">
		</form>
	
	<h2>既存部署編集</h2>
	
	<table>
		<tr>
			<th>部署ID</th>
			<th>部署名</th>
			<th></th>
			<th></th>
		</tr>
		
		<c:forEach var="department" items="${departmentList}">
			<tr>
				<td>${department.departmentId}</td>
				<td>${department.departmentName}</td>
				
				<td>
					<form action="updateDepartment" method="post">
						<input type="hidden" name="departmentId" value="${department.departmentId}">
						<input type="submit" value="修正">
					</form>
				</td>
				
				<td>
					<form action="deleteDepartment" method="post" onsubmit="return confirm('削除しますか？')">
						<input type="hidden" name="departmentId" value="${department.departmentId}">
						<input type="submit" value="削除">
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>
	
	<c:if test="${insertDepartmentMsg != null}">
		${insertDepartmentMsg}
		<c:remove  var="insertDepartmentMsg" scope="session"/>
	</c:if><br>
		
	<c:if test="${updateDepartmentMsg != null}">
		${updateDepartmentMsg}
		<c:remove  var="updateDepartmentMsg" scope="session"/>
	</c:if><br>
		
	<c:if test="${deleteDepartmentMsg != null}">
		${deleteDepartmentMsg}
		<c:remove  var="deleteDepartmentMsg" scope="session"/>
	</c:if><br>
	
	<a href="admin">戻る</a>
</body>
</html>