<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>給料更新</title>
</head>
<body>
	<h1>給料更新</h1>
		<form action="updateSalaryCheck" method="post">
			<input type="hidden" name="salaryId" value="${salary.salaryId}">
			
			<label>ユーザー</label>
			<select name="employeeId">
				<c:forEach var="employee" items="${employeeList}">
					<option value="${employee.employeeId}"
						${salary.employeeId == employee.employeeId ? 'selected' : ''}>
						${employee.name}
					</option>
				</c:forEach>
			</select>
			
			<label>支給額
				<input type="number" name="amount" value="${salary.amount}" required>
			</label>
			
			<label>支給月
				<input type="number" name="month" value="${salary.month}" min="1" max="12" required>
			</label>
			
			<input type="submit" value="更新">
		</form>
</body>
</html>