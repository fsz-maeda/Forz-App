<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>部署修正</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/updateDepartment.css">
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

    <h1 class="page-title">部署修正</h1>

    <form action="updateDepartmentCheck" method="post" class="form-box">

        <input type="hidden" name="departmentId" value="${department.departmentId}">

        <div class="form-group">
            <label>部署名</label>
            <input type="text" name="departmentName" value="${department.departmentName}">
        </div>

        <div class="btn-area">
            <a href="manageDepartment" class="btn-back">戻る</a>
            <button type="submit">修正</button>
        </div>

    </form>

</div>

</body>
</html>