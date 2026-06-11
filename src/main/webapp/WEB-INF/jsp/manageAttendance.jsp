<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>勤怠管理者</title>
</head>
<body>

<table border="1">
    <tr>
        <th>ID</th>
        <th>名前</th>
        <th>部署</th>
    </tr>

    <c:forEach var="emp" items="${attendanceEmployee}">
        <tr>
            <td>${emp.employeeId}</td>
            <td>${emp.name}</td>
            <td>${emp.department}</td>
            <td>
            <form action="manageAttendance" method="post">
	            <input type="hidden" name="employeeId" value="${emp.employeeId }">
	            <button>編集</button>
            </form>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>