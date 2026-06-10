<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>有給管理</title>
</head>
<body>
	<h1>有給管理</h1>
	
	<table>
		<tr>
			<th>有給ID</th>
			<th>ユーザーID</th>
			<th>休暇区分</th>
			<th>使用日数</th>
			<th>開始日</th>
			<th>終了日</th>
			<th>状態</th>
		</tr>
		
		<c:forEach var="holiday" items="${paidHolidayList}">
			<tr>
				<td>${holiday.paidHolidayId}</td>
				<td>${holiday.employeeId}</td>
				<td>${holiday.holidayType}</td>
				<td>${holiday.usedDays}</td>
				<td>${holiday.startDate}</td>
				<td>${holiday.finishDate}</td>
				<td>${holiday.status}</td>
				
				<td>
					<form action="approvePaidHoliday" method="post">
						<input type="hidden" name="paidHolidayId" value="${holiday.paidHolidayId}">
						<input type="submit" value="承認">
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>
	
	<c:if test="${approvePaidHolidayMsg != null}">
		${approvePaidHolidayMsg}
		<c:remove var="approvePaidHolidayMsg" scope="session"/><br>
	</c:if>
	
	<a href="admin">戻る</a>
</body>
</html>