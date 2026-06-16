<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Calendar</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/Calendar.css">
</head>

<body>

<header>
    <div class="header-top">
        <h1><a href="Main">ForzApp</a></h1>
        <div class="header-link">
            <a href="event">イベント一覧</a>
            <button class="hamburger" onclick="toggleMenu()">☰</button>
			<jsp:include page="/WEB-INF/jsp/header.jsp" />
        </div>
    </div>
</header>


<div class="calendar-container">

    <div class="calendar-header">

        <div class="nav">
            <a href="calendar?year=${prevYear}&month=${prevMonth}">← 前月</a>
            <a href="calendar?year=${nextYear}&month=${nextMonth}">翌月 →</a>
        </div>

        <h1>${year}年${month}月</h1>
    </div>

    <hr>

    <table class="calendar-table">

        <tr>
            <th>日</th>
            <th>月</th>
            <th>火</th>
            <th>水</th>
            <th>木</th>
            <th>金</th>
            <th>土</th>
        </tr>

        <c:set var="day" value="1" />

        <c:forEach var="week" begin="1" end="6">

            <tr>

                <c:forEach var="col" begin="0" end="6">

                    <td>
                        <c:choose>

                            <c:when test="${week == 1 && col < startColumn}">
                            </c:when>

                            <c:when test="${day <= lastDay}">

                                <div class="day-number">${day}</div>

                                <c:forEach var="event" items="${eventList}">

                                    <c:if test="${event.eventYear == year
                                        && event.eventMonth == month
                                        && event.eventDay == day}">

                                        <div class="event-item">
                                            ${event.title}
                                        </div>

                                    </c:if>

                                </c:forEach>

                                <c:set var="day" value="${day + 1}" />

                            </c:when>

                        </c:choose>
                    </td>

                </c:forEach>

            </tr>

        </c:forEach>

    </table>

</div>

</body>
</html>