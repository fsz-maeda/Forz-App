<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー修正</title>
</head>
<body>
	<h1>ユーザー修正</h1>
	<form action="updateUser" method="post">
		<input type="hidden" name="employeeId" value="${employee.employeeId }">
		
		役職ID<input type="number" name="positionId" value="${employee.position}" required><br>
		部署ID<input type="number" name="departmentId" value="${employee.department}" required><br>
		入社日<input type="date" name="enter" value="${employee.enter}" required><br>
		有給日数<input type="number" name="remainPaidHoliday" value="${employee.remainPaidHoliday}" required><br>
		管理者権限
			<input type="radio" name="management" value="true"  ${employee.management ? 'checked' : ''}> 許可
    		<input type="radio" name="management" value="false" ${!employee.management ? 'checked' : ''}> 未許可<br>
		
		<input type="submit" value="更新">
	</form>
	
	<table>
		<tr>
			<th>役職ID</th>
			<th>役職名</th>
		</tr>
		
		<c:forEach var="position" items="${positionList}">
			<tr>
				<td>${position.positionId}</td>
				<td>${position.positionName}</td>
			</tr>
		</c:forEach>
	</table>
	
	<table>
		<tr>
			<th>部署ID</th>
			<th>部署名</th>
		</tr>
		
		<c:forEach var="department" items="${departmentList}">
			<tr>
				<td>${department.departmentId}</td>
				<td>${department.departmentId}</td>
			</tr>
		</c:forEach>
	</table>
	
	<a href="manageUser">戻る</a>
</body>
</html>