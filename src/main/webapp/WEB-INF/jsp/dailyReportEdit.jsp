<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>日報修正</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/dailyReportEdit.css">
</head>
<body>
<button class="hamburger" onclick="toggleMenu()">☰</button>
<jsp:include page="/WEB-INF/jsp/header.jsp" />

<form action="DailyReportEditServlet" method="post">
    <input type="hidden" name="reportId" value="${report.dailyReportId}">

    <p>タイトル</p>
    <input type="text" name="title" value="${report.title}" required>

    <p>種別</p>
    <select name="reportType">
        <option value="日報" ${report.reportType == '日報' ? 'selected' : ''}>日報</option>
        <option value="週間レポート" ${report.reportType == '週間レポート' ? 'selected' : ''}>週間レポート</option>
    </select>

    <p>内容</p>
    <textarea name="content" rows="50" cols="100" required>${report.content}</textarea>

    <input type="submit" value="更新">
</form>

</body>
</html>