<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>日報詳細</title>
</head>
<body>
	<a href="dailyReportPage">日報一覧</a>
	
	<h1>${dailyReport.title}</h1>
	
	<div class="post-card">

		<div class="post-header">
			<h3>${dailyReport.title}</h3>

			<div class="meta">
				<span>${dailyReport.userName}</span>
				<span>
					<fmt:formatDate value="${dailyReport.createdAt}" pattern="yyyy-MM-dd HH:mm"/>
				</span>
				<span>${dailyReport.reportType}</span>
			</div>
		</div>

		<div class="post-body">
			<p>${dailyReport.content}</p>
		</div>

		<div class="post-actions">

			<form action="DailyReportLikeServlet" method="post">
				<input type="hidden" name="dailyReportId" value="${dailyReport.dailyReportId}"/>

				<button type="submit" class="like-btn">
					<c:choose>
						<c:when test="${dailyReport.liked}">❤</c:when>
						<c:otherwise>♡</c:otherwise>
					</c:choose>
					${dailyReport.likes}
				</button>
			</form>

		</div>

		<div class="comments">

			<c:forEach var="c" items="${dailyReport.commentList}">
				<div class="comment">
					<span>${c.employeeName} : </span>
					<span>${c.commentText}</span>

					<c:if test="${sessionScope.loginUser.employeeId == c.employeeId}">
						<form action="DailyReportCommentServlet" method="post"  onsubmit="return confirm('本当に削除しますか？');">
							<input type="hidden" name="action" value="delete">
							<input type="hidden" name="commentId" value="${c.commentId}">
							<input type="hidden" name="reportId" value="${dailyReport.dailyReportId}">
							<button type="submit">削除</button>
						</form>
					</c:if>

				</div>
			</c:forEach>

			<form action="DailyReportCommentServlet" method="post" class="comment-form">
				<input type="hidden" name="action" value="insert">
				<input type="hidden" name="reportId" value="${dailyReport.dailyReportId}">
				<input type="text" name="comment" placeholder="コメント..." required>
				<button type="submit">送信</button>
			</form>

		</div>

		<c:if test="${sessionScope.loginUser.employeeId == dailyReport.employeeId}">
			<div class="owner-actions">

				<form action="DailyReportEditServlet" method="get">
					<input type="hidden" name="reportId" value="${dailyReport.dailyReportId}">
					<button>編集</button>
				</form>

				<form action="DailyReportPostServlet" method="post"  onsubmit="return confirm('本当に削除しますか？');">
					<input type="hidden" name="action" value="dailyReportDelete">
					<input type="hidden" name="reportId" value="${dailyReport.dailyReportId}">
					<button class="danger">削除</button>
				</form>

			</div>
		</c:if>

	</div>
</body>
</html>