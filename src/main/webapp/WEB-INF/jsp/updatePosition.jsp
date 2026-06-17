<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>役職修正</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/updatePosition.css">

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

    <h1 class="page-title">役職修正</h1>

    <div class="form-card">

        <form action="updatePositionCheck" method="post">

            <input type="hidden" name="positionId" value="${position.positionId}">

            <div class="form-group">
                <label>役職ID（変更後）</label>
                <input type="number" name="newPositionId" value="${position.positionId}">
            </div>

            <div class="form-group">
                <label>役職名</label>
                <input type="text" name="positionName" value="${position.positionName}">
            </div>

            <div class="btn-area">
            	<a href="managePosition" class="btn btn-secondary">戻る</a>
                <button type="submit">更新</button>
            </div>

        </form>

    </div>

</div>

</body>
</html>