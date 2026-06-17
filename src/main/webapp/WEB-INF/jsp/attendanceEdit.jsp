<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>勤怠詳細</title>
</head>
<body>

<header>
    <div class="header-top">
        <h1><a href="Main">ForzApp</a></h1>
        <div class="header-link">
            <button class="hamburger" onclick="toggleMenu()">☰</button>
			<jsp:include page="/WEB-INF/jsp/header.jsp" />
        </div>
    </div>
</header>


<h2>${date} の勤怠</h2>

<form action="AttendanceEditServlet" method="post">

出勤：
<input type="time" name="clockIn"
    value="${attendance.clockIn != null ? attendance.clockIn.toString().substring(11,16) : ''}">

退勤：
<input type="time" name="clockOut"
    value="${attendance.clockOut != null ? attendance.clockOut.toString().substring(11,16) : ''}">

休憩（分）：
<input type="number" name="breakMinutes"
    value="${attendance.breakMinutes}">

<input type="hidden" name="date" value="${date}">

<button type="submit">保存</button>

</form>


</body>
</html>