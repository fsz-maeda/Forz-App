<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>有給申請</title>
</head>
<body>
	<h1>有給申請</h1>
	
	<h2>申請</h2>
	
	<form action="insertPaidHolidayCheck" method="post">
		<input type="hidden" name="employeeId" value="${loginUser.employeeId}">
		開始日<input type="date" name="startDate" required>
		終了日<input type="date" name="finishDate" required>
		<input type="submit" value="申請">
	</form>
	
	<h2>申請済みの有休</h2>
	
	<table>
		<tr>
			<th>有休ID</th>
			<th>使用日数</th>
			<th>開始日</th>
			<th>終了日</th>
		</tr>
		
		<c:forEach var="holiday" items="${holidayList}">
			<tr>
				<td>${holiday.paidHolidayId}</td>
				<td>${holiday.usedDays}</td>
				<td>${holiday.startDate}</td>
				<td>${holiday.finishDate}</td>
			</tr>
		</c:forEach>
	</table>
	
	<c:if test="${insertPaidHolidayMsg != null}">
		${insertPaidHolidayMsg}
		<c:remove var="insertPaidHolidayMsg" scope="session"/>
	</c:if>
</body>
</html>