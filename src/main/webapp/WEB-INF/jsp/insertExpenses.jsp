<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>経費申請</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/insertExpenses.css">
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

    <h1 class="page-title">💰 経費申請</h1>

    <!-- 申請フォーム -->
    <div class="card">
        <h2>申請</h2>

        <form action="insertExpensesCheck" method="post" class="form-box">

            <div class="form-group">
                <label>申請額</label>
                <input type="number" name="amount">
            </div>

            <div class="form-group">
                <label>詳細</label>
                <input type="text" name="detail">
            </div>

            <button type="submit">申請</button>

        </form>

        <c:if test="${insertExpensesMsg != null}">
            <p class="msg">${insertExpensesMsg}</p>
            <c:remove var="insertExpensesMsg" scope="session"/>
        </c:if>
    </div>

    <!-- 履歴 -->
    <div class="card">
        <h2>申請履歴</h2>

        <table class="table">
            <tr>
                <th>ID</th>
                <th>金額</th>
                <th>詳細</th>
                <th>承認</th>
            </tr>

            <c:forEach var="expenses" items="${expensesList}">
                <tr>
                    <td>${expenses.expensesId}</td>
                    <td>${expenses.amount}</td>
                    <td>${expenses.detail}</td>
                    <td>
                        <c:choose>
                            <c:when test="${expenses.approval == null}">
                                未承認
                            </c:when>
                            <c:otherwise>
                                ${expenses.approval}
                            </c:otherwise>
                        </c:choose>
                    </td>
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