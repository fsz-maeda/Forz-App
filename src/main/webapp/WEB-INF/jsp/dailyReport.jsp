<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>日報</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/dailyReport.css">

</head>

<body>

<header>
	<div class="header-top">
		<h1><a href="Main">ForzApp</a></h1>

		<div class="header-link">

			<form action="DailyReportPostServlet" method="get">
				<input type="hidden" name="action" value="post">
				<button type="submit">＋ 新規作成</button>
			</form>

			<form action="dailyReportPage" method="get">
				<input type="text" name="keyword" placeholder="検索">
				<button type="submit">検索</button>
			</form>

		</div>
	</div>
</header>

<div class="page-title">
	<h2>📝 日報フィード</h2>
</div>

<c:if test="${not empty sessionScope.deleteErrorMsg}">
	<div class="message danger">
		${sessionScope.deleteErrorMsg}
	</div>
	<c:remove var="deleteErrorMsg" scope="session"/>
</c:if>

<div class="feed">

<c:forEach var="r" items="${reportList}">

	<div class="post-card">

		<div class="post-header">
			<h3>${r.title}</h3>

			<div class="meta">
				<span>${r.userName}</span>
				<span>
					<fmt:formatDate value="${r.createdAt}" pattern="yyyy-MM-dd HH:mm"/>
				</span>
				<span>${r.reportType}</span>
			</div>
		</div>

		<div class="post-body">
			<p>${r.content}</p>
		</div>

		<div class="post-actions">

			<form action="DailyReportLikeServlet" method="post">
				<input type="hidden" name="dailyReportId" value="${r.dailyReportId}"/>

				<button type="submit" class="like-btn">
					<c:choose>
						<c:when test="${r.liked}">❤</c:when>
						<c:otherwise>♡</c:otherwise>
					</c:choose>
					${r.likes}
				</button>
			</form>

		</div>

		<div class="comments">

			<c:forEach var="c" items="${r.commentList}">
				<div class="comment">
					<span>${c.employeeName}</span>
					<span>${c.commentText}</span>

					<c:if test="${sessionScope.loginUser.employeeId == c.employeeId}">
						<form action="DailyReportCommentServlet" method="post">
							<input type="hidden" name="action" value="delete">
							<input type="hidden" name="commentId" value="${c.commentId}">
							<input type="hidden" name="reportId" value="${r.dailyReportId}">
							<button type="submit">削除</button>
						</form>
					</c:if>

				</div>
			</c:forEach>

			<form action="DailyReportCommentServlet" method="post" class="comment-form">
				<input type="hidden" name="action" value="insert">
				<input type="hidden" name="reportId" value="${r.dailyReportId}">
				<input type="text" name="comment" placeholder="コメント..." required>
				<button type="submit">送信</button>
			</form>

		</div>

		<c:if test="${sessionScope.loginUser.employeeId == r.employeeId}">
			<div class="owner-actions">

				<form action="DailyReportEditServlet" method="get">
					<input type="hidden" name="reportId" value="${r.dailyReportId}">
					<button>編集</button>
				</form>

				<form action="DailyReportPostServlet" method="post">
					<input type="hidden" name="action" value="dailyReportDelete">
					<input type="hidden" name="reportId" value="${r.dailyReportId}">
					<button class="danger">削除</button>
				</form>

			</div>
		</c:if>

	</div>

</c:forEach>

</div>

<!-- ページネーション -->
<div class="pagination">

	<c:if test="${hasPrev}">
		<a href="dailyReportPage?page=${currentPage - 1}">← 前へ</a>
	</c:if>

	<c:forEach var="i" begin="1" end="${totalPage}">
		<c:choose>
			<c:when test="${i == currentPage}">
				<strong>${i}</strong>
			</c:when>
			<c:otherwise>
				<a href="dailyReportPage?page=${i}">${i}</a>
			</c:otherwise>
		</c:choose>
	</c:forEach>

	<c:if test="${hasNext}">
		<a href="dailyReportPage?page=${currentPage + 1}">次へ →</a>
	</c:if>

</div>

</body>
</html>