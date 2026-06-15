<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>有給修正</title>
</head>
<body>

	<h1>有給修正</h1>

	<form action="updatePaidHolidayCheck" method="post">

		<input type="hidden" name="paidHolidayId"
			value="${paidHoliday.paidHolidayId}"> <input type="hidden"
			name="employeeId" value="${loginUser.employeeId}"> <label>休暇区分</label>
		<select name="holidayType">

			<option value="全休"
				${paidHoliday.holidayType == '全休' ? 'selected' : ''}>全休</option>

			<option value="午前休"
				${paidHoliday.holidayType == '午前休' ? 'selected' : ''}>午前休</option>

			<option value="午後休"
				${paidHoliday.holidayType == '午後休' ? 'selected' : ''}>午後休</option>

		</select> <br>
		<br> <label> 開始日 <input type="date" name="startDate"
			value="${paidHoliday.startDate}" required>
		</label> <br>
		<br> <label> 終了日 <input type="date" name="finishDate"
			value="${paidHoliday.finishDate}" required>
		</label> <br>
		<br> <input type="submit" value="更新">

	</form>

</body>
</html>