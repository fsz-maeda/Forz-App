<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>有給承認</title>
</head>
<body>
	<h1>有給承認</h1>
	
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
		
		<tr>
			<td>${holiday.paidHolidayId}</td>
			<td>${holiday.employeeId}</td>
			<td>${holiday.holidayType}</td>
			<td>${holiday.usedDays}</td>
			<td>${holiday.startDate}</td>
			<td>${holiday.finishDate}</td>
			<td>${holiday.status}</td>
		</tr>
	</table>
	
	<form action="approvePaidHolidayCheck" method="post">
		<input type="hidden" name="paidHolidayId" value="${holiday.paidHolidayId}">
		<input type="hidden" name="employeeId" value="${holiday.employeeId}">
		
		承認
		<select name="status">
        	<option value="承認済み">承認</option>
        	<option value="却下">却下</option>
    	</select>
    	
    	<input type="submit" value="登録">
	</form>
</body>
</html>