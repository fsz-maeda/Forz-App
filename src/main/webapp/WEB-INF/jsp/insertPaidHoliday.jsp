<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>有給申請</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/insertPaidholiday.css">
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

    <h1 class="page-title">🌿 有給申請</h1>

    <!-- 申請フォーム -->
    <div class="card">

        <h2>申請</h2>

        <form action="insertPaidHolidayCheck" method="post" class="form-box">

            <input type="hidden" name="employeeId" value="${loginUser.employeeId}">

            <div class="form-group">
                <label>休暇区分</label>
                <select name="holidayType">
                    <option value="全休">全休</option>
                    <option value="午前休">午前休</option>
                    <option value="午後休">午後休</option>
                </select>
            </div>

            <div class="form-group">
                <label>開始日</label>
                <input type="date" name="startDate" required>
            </div>

            <div class="form-group">
                <label>終了日</label>
                <input type="date" name="finishDate" required>
            </div>

            <button type="submit">申請</button>

        </form>

        <c:if test="${insertPaidHolidayMsg != null}">
            <p class="msg">${insertPaidHolidayMsg}</p>
            <c:remove var="insertPaidHolidayMsg" scope="session"/>
        </c:if>

        <c:if test="${updatePaidHolidayMsg != null}">
            <p class="msg">${updatePaidHolidayMsg}</p>
            <c:remove var="updatePaidHolidayMsg" scope="session"/>
        </c:if>

        <c:if test="${deletePaidHolidayMsg != null}">
            <p class="msg">${deletePaidHolidayMsg}</p>
            <c:remove var="deletePaidHolidayMsg" scope="session"/>
        </c:if>

    </div>

    <!-- 履歴 -->
    <div class="card">

        <h2>申請済みの有休</h2>

        <table class="table">
            <tr>
                <th>ID</th>
                <th>日数</th>
                <th>開始日</th>
                <th>終了日</th>
                <th>区分</th>
                <th>状態</th>
                <th colspan="2">操作</th>
            </tr>

            <c:forEach var="holiday" items="${holidayList}">
				<tr>
					<td>${holiday.paidHolidayId}</td>
					<td>${holiday.usedDays}</td>
					<td>${holiday.startDate}</td>
					<td>${holiday.finishDate}</td>
					<td>${holiday.holidayType}</td>
					<td>${holiday.status}</td>
				
					<c:if test="${holiday.status == '申請中'}">
						<td>
							<form action="updatePaidHoliday" method="post">
								<input type="hidden" name="paidHolidayId"
								       value="${holiday.paidHolidayId}">
								<button type="submit">修正</button>
							</form>
						</td>
				
						<td>
							<form action="deletePaidHoliday" method="post"
							      onsubmit="return confirm('削除しますか？')">
								<input type="hidden" name="paidHolidayId"
								       value="${holiday.paidHolidayId}">
								<button type="submit">削除</button>
							</form>
						</td>
					</c:if>
				
					<c:if test="${holiday.status != '申請中'}">
						<td colspan="2">-</td>
					</c:if>
				</tr>
				</c:forEach>

        </table>

    </div>

    <!-- 戻る -->
    <div class="nav-back">
        <a href="application" class="btn-back">戻る</a>
    </div>

</div>

</body>
</html>