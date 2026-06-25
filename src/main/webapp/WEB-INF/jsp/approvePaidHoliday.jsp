<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>有給承認確認</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/approvePaidHoliday.css">
</head>

<body>

<header>
    <h1>有給承認確認</h1>
</header>

<div class="container">

    <table border="1">
        <tr>
            <th>ID</th>
            <th>ユーザーID</th>
            <th>区分</th>
            <th>日数</th>
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

        <label>承認ステータス</label>
        <select name="status">
            <option value="承認">承認</option>
            <option value="却下">却下</option>
        </select>

        <button type="submit">確定</button>
    </form>

</div>

</body>
</html>