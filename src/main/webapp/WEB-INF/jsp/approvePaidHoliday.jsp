<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>有給承認</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/approvePaidHoliday.css">
</head>

<body>

<!-- 共通ヘッダー -->
<header>
    <div class="header-top">
        <h1><a href="Main">ForzApp</a></h1>

        <div class="header-link">
            <button class="hamburger" onclick="toggleMenu()">☰</button>
            <jsp:include page="/WEB-INF/jsp/header.jsp" />
        </div>
    </div>
</header>

<div class="container">

    <h1 class="page-title">有給承認</h1>

    <!-- 情報カード -->
    <div class="card">

        <table class="table">
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

    </div>

    <!-- 承認フォーム -->
    <div class="form-card">

        <form action="approvePaidHolidayCheck" method="post">

            <input type="hidden" name="paidHolidayId" value="${holiday.paidHolidayId}">
            <input type="hidden" name="employeeId" value="${holiday.employeeId}">

            <label>承認ステータス</label>

            <select name="status">
                <option value="承認済み">承認</option>
                <option value="却下">却下</option>
            </select>

            <button type="submit">登録</button>

        </form>

    </div>

</div>

<script>
function toggleMenu() {
    document.getElementById("sideMenu").classList.toggle("open");
}
</script>

</body>
</html>