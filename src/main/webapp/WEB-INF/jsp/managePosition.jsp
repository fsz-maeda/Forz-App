<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>役職管理</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/managePosition.css">

</head>

<body>

<header>
	<div class="header-top">
		<h1><a href="admin">ForzApp</a></h1>
		<div class="header-link">
			<p><a href="admin">管理者ページへ</a></p>
		</div>
	</div>
</header>

<header>
    <div class="header-top">
        <h1><a href="Main">ForzApp</a></h1>
        <div class="header-link">
        	<p><a href="admin">管理者ページへ</a></p>
            <button class="hamburger" onclick="toggleMenu()">☰</button>
			<jsp:include page="/WEB-INF/jsp/header.jsp" />
        </div>
    </div>
</header>


<div class="word">
	<h2>👔 役職管理</h2>
	<p>役職の追加・変更・削除</p>
</div>

<hr>

<div class="main">

	<!-- 左：新規追加 -->
	<div class="card">

		<h3>➕ 新規役職追加</h3>

		<form action="insertPosition" method="post">
			<button type="submit" class="btn-primary">＋ 追加</button>
		</form>

	</div>

	<!-- 右：一覧 -->
	<div class="table-card">

		<h3>📋 既存役職一覧</h3>

		<table>

			<thead>
				<tr>
					<th>ID</th>
					<th>役職名</th>
					<th>修正</th>
					<th>削除</th>
				</tr>
			</thead>

			<tbody>

				<c:forEach var="position" items="${positionList}">
					<tr>
						<td>${position.positionId}</td>
						<td>${position.positionName}</td>

						<td>
							<form action="updatePosition" method="post">
								<input type="hidden" name="positionId" value="${position.positionId}">
								<button type="submit" class="btn-edit">修正</button>
							</form>
						</td>

						<td>
							<form action="deletePosition" method="post"
								  onsubmit="return confirm('削除しますか？')">
								<input type="hidden" name="positionId" value="${position.positionId}">
								<button type="submit" class="btn-delete">削除</button>
							</form>
						</td>

					</tr>
				</c:forEach>

			</tbody>

		</table>

	</div>

</div>

<!-- メッセージ -->
<c:if test="${insertPositionMsg != null}">
	<div class="message success">${insertPositionMsg}</div>
	<c:remove var="insertPositionMsg" scope="session"/>
</c:if>

<c:if test="${updatePositionMsg != null}">
	<div class="message info">${updatePositionMsg}</div>
	<c:remove var="updatePositionMsg" scope="session"/>
</c:if>

<c:if test="${deletePositionMsg != null}">
	<div class="message danger">${deletePositionMsg}</div>
	<c:remove var="deletePositionMsg" scope="session"/>
</c:if>

<br>

</body>
</html>