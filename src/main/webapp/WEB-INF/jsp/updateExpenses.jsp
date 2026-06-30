<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>経費審査</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/updateExpenses.css">
</head>

<body>

<!-- 共通ヘッダー -->
<header>
    <div class="header-top">
        <h1><a href="Main">ForzApp</a></h1>
        <div class="header-link">
        	<a href="manageExpenses">経費管理ページへ戻る</a>
            <button class="hamburger" onclick="toggleMenu()">☰</button>
			<jsp:include page="/WEB-INF/jsp/header.jsp" />
        </div>
    </div>
</header>

<div class="container">

    <h1 class="page-title">💰 経費審査</h1>

    <!-- 申請内容 -->
    <div class="card">

        <table class="table">
            <tr>
                <th>ID</th>
                <th>従業員ID</th>
                <th>金額</th>
                <th>詳細</th>
                <th>状態</th>
            </tr>

            <tr>
                <td>${expenses.expensesId}</td>
                <td>${expenses.employeeId}</td>
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

        </table>

    </div>

    <!-- 審査フォーム -->
    <div class="card">

        <h2>審査</h2>

        <form action="updateExpensesCheck" method="post" class="form-box">

            <input type="hidden" name="expensesId" value="${expenses.expensesId}">

            <div class="form-group">
                <label>審査結果</label>

                <label>
                    <input type="radio" name="approval" value="承認">
                    承認
                </label>

                <label>
                    <input type="radio" name="approval" value="否決">
                    否決
                </label>

            </div>

            <button type="submit">確定</button>

        </form>

    </div>

    <!-- 戻る -->
    <div class="nav-back">
        <a href="manageExpenses" class="btn-back">戻る</a>
    </div>

</div>

</body>
</html>