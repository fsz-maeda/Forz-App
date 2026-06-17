<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>給与</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/salary.css">

</head>

<body>

<header>
    <div class="header-top">
        <h1><a href="Main">ForzApp</a></h1>
        <div class="header-link">
            <button class="hamburger" onclick="toggleMenu()">☰</button>
			<jsp:include page="/WEB-INF/jsp/header.jsp" />
        </div>
    </div>
</header>


<div class="page-title">
	<h2>💰 給与明細</h2>
</div>

<div class="salary-wrapper">

<c:forEach var="salary" items="${salaryList}">

	<div class="salary-card">

		<div class="salary-amount">
			<span>支給額</span>
			<strong>¥${salary.amount}</strong>
		</div>

		<div class="salary-month">
			<span>支給月</span>
			<strong>${salary.month}</strong>
		</div>

	</div>

</c:forEach>

</div>

</body>
</html>