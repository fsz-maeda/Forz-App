<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>${media.title}</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/articleContnt.css">
</head>
<body>

	<div class="container">

		<!-- 記事ヘッダー -->
		<div class="article-header">
			<h1>${media.title}</h1>
			
			<c:if test="${loginUser != null && loginUser.employeeId == media.employeeId}">
				<div class="action-group">

					<form action="MediaEditServlet" method="get"
						style="display: inline;">
						<input type="hidden" name="id" value="${media.id}">
						<button type="submit" class="btn btn-sm btn-secondary">
							この記事を編集する</button>
					</form>

					<form action="MediaDeleteServlet" method="post" style="display: inline;"
						onsubmit="return confirm('本当にこの記事を削除しますか？');">

						<input type="hidden" name="id" value="${media.id}">

						<button type="submit" class="btn btn-sm btn-danger">この記事を削除する</button>
					</form>
				</div>
			</c:if>
		</div>

		<!-- 記事本文 -->
		<div class="article-content">${media.content}</div>
		
		<!-- フッター -->
		<div class="article-footer">
			<a href="media" class="btn btn-secondary"> ← メディア画面へ </a> <a
				href="MediaCommentServlet" class="btn btn-primary"> コメントを書く </a>
		</div>
		
		<h2>いいね</h2>
		
		<form action="mediaLikes" method="post">
			<input type="hidden" name="mediaId" value="${media.id}"/>

			<button type="submit">
				<c:choose>
					<c:when test="${status.liked}">❤</c:when>
					<c:otherwise>♡</c:otherwise>
				</c:choose>
				${status.likes}
			</button>
		</form>

		<!-- コメント一覧 -->
		<div class="comment-section">
			<h2>コメント一覧</h2>

			<table class="comment-table">
				<thead>
					<tr>
						<th class="comment-name">名前</th>
						<th>コメント</th>
					</tr>
				</thead>

				<tbody>
					<c:choose>
						<c:when test="${not empty commentlist}">
							<c:forEach var="mc" items="${commentlist}">
								<tr>
									<td class="comment-name">${mc.name}</td>
									<td style="white-space: pre-wrap;">${mc.comment}</td>
								</tr>
							</c:forEach>
						</c:when>

						<c:otherwise>
							<tr>
								<td colspan="2">まだコメントはありません</td>
							</tr>
						</c:otherwise>

					</c:choose>

				</tbody>
			</table>
		</div>

	</div>

</body>
</html>