<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>アンケート一覧</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/manageQuestion.css">

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




<div class="page-title">
	<h2>📊 アンケート一覧</h2>
</div>

<div class="list-wrapper">

<c:forEach var="question" items="${questionnaireList}">

	<div class="card">

		<div class="card-header">
			<span>ID: ${question.questionnaireId}</span>
			<span>ユーザーID: ${question.employeeId}</span>
		</div>

		<div class="card-body">
			<p>${question.content}</p>
		</div>

	</div>

</c:forEach>

</div>

</body>
</html>