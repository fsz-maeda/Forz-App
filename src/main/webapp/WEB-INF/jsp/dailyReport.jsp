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

<!-- ================= HEADER ================= -->
<header>
    <div class="header-top">
        <h1><a href="Main">ForzApp</a></h1>

        <div class="header-link">

            <!-- 新規作成 -->
            <form action="DailyReportPostServlet" method="get">
                <input type="hidden" name="action" value="post">
                <button type="submit">＋ 新規作成</button>
            </form>

            <!-- 検索 -->
            <form action="dailyReportPage" method="get">
                <input type="text" name="keyword" value="<c:out value='${keyword}'/>" placeholder="検索">
                <button type="submit">検索</button>
            </form>

            <button class="hamburger" onclick="toggleMenu()">☰</button>
            <jsp:include page="/WEB-INF/jsp/header.jsp" />

        </div>
    </div>
</header>

<!-- ================= TITLE ================= -->
<div class="page-title">
    <h2>📝 日報フィード</h2>
</div>

<!-- ================= FEED ================= -->
<div class="feed">

<c:forEach var="report" items="${reportList}">

    <div class="post-card">

        <!-- ===== POST HEADER ===== -->
        <div class="post-header">
            <h3><c:out value="${report.title}" /></h3>

            <div class="meta">
                <span><c:out value="${report.userName}" /></span>
                <span>
                    <fmt:formatDate value="${report.createdAt}" pattern="yyyy-MM-dd HH:mm"/>
                </span>
                <span><c:out value="${report.reportType}" /></span>
            </div>
        </div>

        <!-- ===== POST BODY ===== -->
        <div class="post-body">
            <p><c:out value="${report.content}" /></p>
        </div>

        <!-- ===== LIKE ===== -->
        <div class="post-actions">

            <form action="DailyReportLikeServlet" method="post">
                <input type="hidden" name="dailyReportId" value="${report.dailyReportId}"/>
                <input type="hidden" name="csrf" value="${sessionScope.csrfToken }">

                <button type="submit" class="like-btn">
                    <c:choose>
                        <c:when test="${report.liked}">❤</c:when>
                        <c:otherwise>♡</c:otherwise>
                    </c:choose>
                    ${report.likes}
                </button>
            </form>

        </div>

        <!-- ===== COMMENTS ===== -->
        <div class="comments">

            <c:forEach var="comment" items="${report.commentList}">

                <div class="comment">
                    <span><c:out value="${comment.employeeName}" /> : </span>
                    <span><c:out value="${comment.commentText}" /></span>

                    <c:if test="${sessionScope.loginUser.employeeId == comment.employeeId}">
                        <form action="DailyReportCommentServlet" method="post"
                              onsubmit="return confirm('本当に削除しますか？');">

                            <input type="hidden" name="action" value="delete">
                            <input type="hidden" name="csrf" value="${sessionScope.csrfToken }">
                            <input type="hidden" name="commentId" value="${comment.commentId}">
                            <input type="hidden" name="reportId" value="${report.dailyReportId}">
                            <button type="submit" class="delete">削除</button>

                        </form>
                    </c:if>

                </div>

            </c:forEach>

            <!-- コメント投稿 -->
            <form action="DailyReportCommentServlet" method="post" class="comment-form">

                <input type="hidden" name="action" value="insert">
                <input type="hidden" name="csrf" value="${sessionScope.csrfToken }">
                <input type="hidden" name="reportId" value="${report.dailyReportId}">
                <input type="text" name="comment" placeholder="コメント..." required>

                <button type="submit">送信</button>

            </form>

        </div>

        <!-- ===== OWNER ACTIONS ===== -->
        <c:if test="${sessionScope.loginUser.employeeId == report.employeeId}">
            <div class="owner-actions">

                <form action="DailyReportEditServlet" method="get">
                    <input type="hidden" name="reportId" value="${report.dailyReportId}">
                    <button>編集</button>
                </form>

                <form action="DailyReportPostServlet" method="post"
                      onsubmit="return confirm('本当に削除しますか？');">
					<input type="hidden" name="csrf" value="${sessionScope.csrfToken}">
                    <input type="hidden" name="action" value="dailyReportDelete">
                    <input type="hidden" name="reportId" value="${report.dailyReportId}">
                    <button class="danger">削除</button>

                </form>

            </div>
        </c:if>

    </div>

</c:forEach>

</div>

<!-- ================= PAGINATION ================= -->
<div class="pagination">

    <c:if test="${hasPrev}">
        <a href="dailyReportPage?page=${currentPage - 1}">← 前へ</a>
    </c:if>

    <c:forEach var="pageNum" begin="1" end="${totalPage}">
        <c:choose>
            <c:when test="${pageNum == currentPage}">
                <strong>${pageNum}</strong>
            </c:when>
            <c:otherwise>
                <a href="dailyReportPage?page=${pageNum}">${pageNum}</a>
            </c:otherwise>
        </c:choose>
    </c:forEach>

    <c:if test="${hasNext}">
        <a href="dailyReportPage?page=${currentPage + 1}">次へ →</a>
    </c:if>

</div>

<!-- ================= SCRIPT ================= -->
<script>
  window.addEventListener("beforeunload", function () {
    sessionStorage.setItem("scrollY", window.scrollY);
  });

  window.addEventListener("load", function () {
    const y = sessionStorage.getItem("scrollY");

    if (y !== null) {
      window.scrollTo(0, parseInt(y));
      sessionStorage.removeItem("scrollY");
    }
  });
</script>

</body>
</html>