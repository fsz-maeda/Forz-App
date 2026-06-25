<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>給料更新</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/updateSalary.css">

</head>
<body>

<header>
    <div class="header-top">
        <h1><a href="Main">ForzApp</a></h1>
        <div class="header-link">
        	<a href="manageUser">戻る</a>
            <button class="hamburger" onclick="toggleMenu()">☰</button>
			<jsp:include page="/WEB-INF/jsp/header.jsp" />
        </div>
    </div>
</header>

<div class="page-title">
    <h2>💰 給料更新</h2>
</div>

<div class="salary-wrapper">
    <div class="salary-card">

    <form action="updateSalaryCheck" method="post">

        <input type="hidden"
               name="salaryId"
               value="${salary.salaryId}">

        <label>ユーザー</label>
        <select name="employeeId">
            <c:forEach var="employee" items="${employeeList}">
                <option value="${employee.employeeId}"
                    ${salary.employeeId == employee.employeeId ? 'selected' : ''}>
                    ${employee.name}
                </option>
            </c:forEach>
        </select>

        <label>支給額</label>
        <input type="number"
               name="amount"
               value="${salary.amount}"
               required>

        <label>支給月</label>
        <input type="number"
               name="month"
               value="${salary.month}"
               min="1"
               max="12"
               required>

        <input type="submit" value="更新">

    </form>

</div>

</div>

</body>
</html>
