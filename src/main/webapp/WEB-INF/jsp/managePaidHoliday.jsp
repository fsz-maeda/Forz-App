<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>有給管理</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/managePaidHoliday.css">

</head>

<body>

<header>
    <div class="header-top">
        <h1><a href="Main">ForzApp</a></h1>
        <div class="header-link">
        	<a href="admin">管理者ページへ戻る</a>
            <button class="hamburger" onclick="toggleMenu()">☰</button>
			<jsp:include page="/WEB-INF/jsp/header.jsp" />
        </div>
    </div>
</header>

<div class="word">
	<h2>📅 有給管理</h2>
	<p>申請一覧と承認処理</p>
</div>

<hr>

<div class="main">

	<div class="table-card">

		<table>
			<thead>
				<tr>
					<th>ID</th>
					<th>ユーザーID</th>
					<th>区分</th>
					<th>日数</th>
					<th>開始日</th>
					<th>終了日</th>
					<th>状態</th>
					<th>操作</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach var="holiday" items="${paidHolidayList}">
					<tr>
						<td>${holiday.paidHolidayId}</td>
						<td>${holiday.employeeId}</td>
						<td>${holiday.holidayType}</td>
						<td>${holiday.usedDays}</td>
						<td>${holiday.startDate}</td>
						<td>${holiday.finishDate}</td>
						<td>
							<span class="status">${holiday.status}</span>
						</td>
						<td>
							<form action="approvePaidHoliday" method="post">
								<input type="hidden" name="paidHolidayId" value="${holiday.paidHolidayId}">
								<button type="submit" class="btn-approve">承認</button>
							</form>
						</td>
					</tr>
				</c:forEach>
			</tbody>

		</table>

	</div>

</div>

<c:if test="${approvePaidHolidayMsg != null}">
	<div class="message">
		${approvePaidHolidayMsg}
	</div>
	<c:remove var="approvePaidHolidayMsg" scope="session"/>
</c:if>

</body>
</html>