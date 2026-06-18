<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>部署メディア一覧</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/media.css">

</head>

<body>

<header>
	<div class="header-top">
		<h1><a href="Main">ForzApp</a></h1>
		<div class="header-link">
			<a href="MediaPostServlet">＋新規投稿</a>
			<button class="hamburger" onclick="toggleMenu()">☰</button>
			<jsp:include page="/WEB-INF/jsp/header.jsp" />
		</div>
	</div>
</header>

<div class="page">

	<h2 class="page-title">🏢 部署コミュニティ一覧</h2>

	<!-- フィルター -->
	<form action="SearchCategoryServlet" method="get" class="filter-box">

		<select name="searchCategory">

			<option value="all" ${selectedType == 'all' ? 'selected' : ''}>すべて</option>
			<option value="業務ナレッジ" ${selectedType == '業務ナレッジ' ? 'selected' : ''}>業務ナレッジ</option>
			<option value="部署内連絡・進歩共有" ${selectedType == '部署内連絡・進歩共有' ? 'selected' : ''}>共有</option>
			<option value="メンバーシップ・相互理解" ${selectedType == 'メンバーシップ・相互理解' ? 'selected' : ''}>交流</option>
			<option value="その他" ${selectedType == 'その他' ? 'selected' : ''}>その他</option>

		</select>

		<button type="submit">絞り込み</button>

	</form>

	<!-- 一覧（カード化） -->
	<div class="media-list">

		<c:choose>

			<c:when test="${not empty medialist}">

				<c:forEach var="media" items="${medialist}" varStatus="status">

					<div class="media-card">

						<div class="media-header">

							<span class="badge">${media.mediaType}</span>
							<span class="date">${media.mediaDate}</span>

						</div>

						<div class="media-title">
							<a href="ArticleContentServlet?mediaId=${media.ID}">
								${media.title}
							</a>
						</div>

						<div class="media-footer">

							<div class="likes">❤ ${media.likesCount}</div>

							<div class="author">
								<c:forEach var="employee" items="${employeeList}">
									<c:if test="${media.employeeId == employee.employeeId}">
										${employee.name}
									</c:if>
								</c:forEach>
							</div>

						</div>

					</div>

				</c:forEach>

			</c:when>

			<c:otherwise>
				<p class="no-data">表示する記事がありません</p>
			</c:otherwise>

		</c:choose>

	</div>

</div>

</body>
</html>