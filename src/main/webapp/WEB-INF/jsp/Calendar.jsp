<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Calendar</title>

<style>
table {
	border-collapse: collapse;
	width: 100%;
}

th, td {
	border: 1px solid black;
	width: 14%;
	height: 120px;
	vertical-align: top;
	padding: 5px;
}

th {
	background-color: #005bac;
	color: white;
}
</style>

</head>
<body>

	<h1>${year}年${month}月</h1>


	<table>

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

					<td><c:choose>
							<c:when test="${week == 1 && col < startColumn}">
							</c:when>
                        <c:when test="${day <= lastDay}">

	<div>
		${day}
	</div>

	<c:forEach var="event" items="${eventList}">

		<c:if test="${event.eventDate.date == day}">

			<div
				style="font-size:12px;
				background:#e8f2ff;
				margin-top:3px;
				padding:2px;
				border-radius:3px;">

				${event.title}

			</div>

		</c:if>

	</c:forEach>

	<c:set var="day" value="${day + 1}" />

</c:when>
							

						</c:choose></td>

				</c:forEach>

			</tr>

		</c:forEach>
	</table>

</body>
</html>