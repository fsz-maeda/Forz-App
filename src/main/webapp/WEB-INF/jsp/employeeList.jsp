<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>   

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>社員一覧</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/employeeList.css">

</head>

<body>

<header>
    <div class="header-top">
        <h1><a href="Main">ForzApp</a></h1>
        <div class="header-link">
        <a href="Main">戻る</a>
            <button class="hamburger" onclick="toggleMenu()">☰</button>
			<jsp:include page="/WEB-INF/jsp/header.jsp" />
        </div>
    </div>
</header>

<div class="page">

	<h2 class="page-title">👥 社員一覧</h2>

	<form class="search-box" action="EmployeeListServlet" method="get">
		<input type="text" name="keyword" placeholder="社員名検索">
		<button type="submit">Search</button>
	</form>

	<div class="nav-back">
		<c:choose>
			<c:when test="${keyword == null}">
			</c:when>
			<c:otherwise>
				<a href="EmployeeListServlet">一覧へ戻る</a>
			</c:otherwise>
		</c:choose>
	</div>

	<div class="employee-list">

		<c:forEach var="emp" items="${employeeList}">

			<div class="employee-card">

				<div class="emp-header">
					<span class="emp-id">ID: ${emp.employeeId}</span>
					<span class="emp-name">${emp.name}</span>
				</div>

				<div class="emp-info">

					<div>
						部署：
						<c:forEach var="d" items="${departmentList}">
							<c:if test="${d.departmentId == emp.department}">
								${d.departmentName}
							</c:if>
						</c:forEach>
					</div>

					<div>
						役職：
						<c:forEach var="p" items="${positionList}">
							<c:if test="${p.positionId == emp.position}">
								${p.positionName}
							</c:if>
						</c:forEach>
					</div>

				</div>

				<div class="emp-actions">
					<a href="ProfileServlet?employeeId=${emp.employeeId}">Profile</a>
					<a href="ChatServlet?receiverId=${emp.employeeId}">Chat</a>
				</div>

			</div>

		</c:forEach>

	</div>

</div>

</body>
</html>