<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>勤怠カレンダー</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>

<body>

<h1>${year}年${month}月</h1>

<div style="display:flex; justify-content:space-between; max-width:900px; margin-bottom:10px;">

    <a href="AttendanceServlet?year=${month == 1 ? year - 1 : year}&month=${month == 1 ? 12 : month - 1}">
        ← 前月
    </a>

    <a href="AttendanceServlet?year=${month == 12 ? year + 1 : year}&month=${month == 12 ? 1 : month + 1}">
        次月 →
    </a>

</div>

<h2>${year}年${month}月</h2>

<div class="week">
    <div>日</div><div>月</div><div>火</div>
    <div>水</div><div>木</div><div>金</div><div>土</div>
</div>

<div class="calendar">

<c:forEach begin="1" end="${days}" var="i">

    <c:set var="date"
        value="${year}-${month < 10 ? '0' : ''}${month}-${i < 10 ? '0' : ''}${i}" />

    <c:set var="a" value="${attendanceMap[date]}" />
    <c:set var="locked" value="${lockMap[date]}" />
    <c:set var="disabled" value="${locked or approved}" />

    <c:choose>

        <c:when test="${disabled}">
            <div class="day locked">
                <div class="date">${i}</div>

                <div class="info">
                    <c:if test="${a != null}">
                        <div class="time">
                            ${a.clockIn} - ${a.clockOut}
                        </div>
                        <div class="break">
                            休憩:${a.breakMinutes}分
                        </div>
                    </c:if>
                </div>
            </div>
        </c:when>

        <c:otherwise>
            <a class="day" href="AttendanceEditServlet?date=${date}">
                <div class="date">${i}</div>

                <div class="info">
                    <c:if test="${a != null}">
                        <div class="time">
                            ${a.clockIn} - ${a.clockOut}
                        </div>
                        <div class="break">
                            休憩:${a.breakMinutes}分
                        </div>
                    </c:if>
                </div>
            </a>
        </c:otherwise>

    </c:choose>

</c:forEach>

</div>

月合計勤務時間：<b>${totalHoursText}</b>

<c:if test="${not approved and sessionScope.loginUser.management}">
    <form action="AttendanceApproveServlet" method="post">
        <input type="hidden" name="year" value="${year}">
        <input type="hidden" name="month" value="${month}">
        <button type="submit">この月を承認</button>
    </form>
</c:if>

<c:if test="${approved and sessionScope.loginUser.management}">
    <form action="AttendanceUnapproveServlet" method="post">
        <input type="hidden" name="year" value="${year}">
        <input type="hidden" name="month" value="${month}">
        <button>承認解除</button>
    </form>
</c:if>

<c:choose>
    <c:when test="${approved}">
        <div style="color:green; font-weight:bold;">
            ✔ 承認済み
        </div>
    </c:when>
    <c:otherwise>
        <div style="color:orange; font-weight:bold;">
            ⏳ 未承認
        </div>
    </c:otherwise>
</c:choose>

<c:if test="${loginUser.management}">
    <a href="AdminSettingServlet">締め日更新画面</a>
</c:if>

<style>
.calendar {
    display: grid;
    grid-template-columns: repeat(7, 1fr);
    gap: 6px;
    max-width: 900px;
}

.day {
    height: 120px;
    border-radius: 10px;
    border: 1px solid #ddd;
    padding: 6px;
    background: white;
    position: relative;
}

.day:hover {
    transform: translateY(-2px);
    box-shadow: 0 4px 10px rgba(0,0,0,0.15);
}

.date {
    display: block;
}
.info {
    margin-top: 20px;
}

.time {
    font-size: 11px;
    color: #333;
}

.break {
    font-size: 11px;
    color: #777;
}

.worked {
    background: #d1f7d6;
}

.partial {
    background: #fff3b0;
}

.none {
    background: #fafafa;
}

.today {
    border: 2px solid #ff4d4d;
}

.empty {
    background: transparent;
    border: none;
    box-shadow: none;
}

.week {
    display: grid;
    grid-template-columns: repeat(7, 1fr);
    max-width: 900px;
    text-align: center;
    font-weight: bold;
    margin-bottom: 5px;
}

.locked {
    background: #ddd;
    color: #888;
    pointer-events: none;
}
</style>

</body>
</html>