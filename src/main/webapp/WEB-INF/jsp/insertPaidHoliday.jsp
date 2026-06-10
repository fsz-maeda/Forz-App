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
		
		休暇区分
    	<select name="holidayType">
        	<option value="全休">全休</option>
        	<option value="午前休">午前休</option>
        	<option value="午後休">午後休</option>
    	</select>
    
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
			<th>休暇区分</th>
			<th>状態</th>
		</tr>
		
		<c:forEach var="holiday" items="${holidayList}">
			<tr>
				<td>${holiday.paidHolidayId}</td>
				<td>${holiday.usedDays}</td>
				<td>${holiday.startDate}</td>
				<td>${holiday.finishDate}</td>
				
				<td>
					<c:choose>
						<c:when test="${holiday.holidayType == '全休'}">
							全休
						</c:when>
						<c:when test="${holiday.holidayType == '午前休'}">
							午前休
						</c:when>
						<c:when test="${holiday.holidayType == '午後休'}">
        					午後休
    					</c:when>
    					<c:otherwise>
        					不明
   			 			</c:otherwise>
					</c:choose>
				</td>
				
				<td>${holiday.status}</td>
				
				<c:if test="${holiday.status == '申請中'}">
					<td>
						<form action="">
							<input type="submit" value="修正"> 
						</form>
					</td>
					
					<td>
						<form action="">
						<input type="submit" value="削除"> 
						</form>
					</td>
				</c:if>
			</tr>
		</c:forEach>
	</table>
	
	<c:if test="${insertPaidHolidayMsg != null}">
		${insertPaidHolidayMsg}
		<c:remove var="insertPaidHolidayMsg" scope="session"/><br>
	</c:if>
	
	<a href="Main">戻る</a>
</body>
</html>