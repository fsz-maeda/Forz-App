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
</head>
<body>
	<header class="header-top">
		<h1>日報</h1>
		<div class="new-post">
			<form action="DailyReportPostServlet" method="get">
				<input type="hidden" name="action" value="post">
				<input type="submit" value="新規記事作成">
			</form>
			<a href="Main">メイン画面へ</a>
			<a href="Home">ホーム画面へ</a>
		</div>
	</header>
	
<c:forEach var="r" items="${reportList}">

    <div class="report-list" style="border:1px solid #ccc; margin:10px; padding:10px;">
        <p>投稿者：${r.userName}</p>
        <p>種別：${r.reportType}</p>
        <p>タイトル：${r.title}</p>
        <p>内容：${r.content}</p>
        <c:if test="${not empty r.createdAt}">
		    <fmt:formatDate value="${r.createdAt}" pattern="yyyy-MM-dd HH:mm"/>
		</c:if>
		<form action="DailyReportLikeServlet" method="post">
		    <input type="hidden" name="dailyReportId" value="${r.dailyReportId}"/>
		    <c:choose>
		        <c:when test="${r.liked}">
		            <button type="submit" class="action-btn">いいね解除</button>
		        </c:when>
		
		        <c:otherwise>
		            <button type="submit" class="action-btn">いいね</button>
		        </c:otherwise>
		    </c:choose>
		</form>
		
		${r.likes}

        

		
	        <c:forEach var="c" items="${r.commentList}">
	           <div style="margin-left:20px;">
	               <p>${c.userName}：${c.commentText}</p>
	           </div>
	           <c:if test="${sessionScope.loginUser.userId == c.userId}">
				    <form action="DailyReportCommentServlet" method="post">
				        <input type="hidden" name="action" value="delete">
				        <input type="hidden" name="commentId" value="${c.commentId}">
				        <input type="hidden" name="reportId" value="${r.dailyReportId}">
				        <button type="submit" class="action-btn">コメント削除</button>
				    </form>
				</c:if>
	        </c:forEach>

	        
		<form action="DailyReportCommentServlet" method="post">
			<input type="hidden" name="action" value="insert">
		    <input type="hidden" name="reportId" value="${r.dailyReportId}">
		    <input type="text" name="comment" required>
		
		    <input type="submit" value="コメント投稿" class="action-btn" >
		
		</form>
		
			<c:if test="${not empty sessionScope.deleteErrorMsg }">
				<p>${sessionScope.deleteErrorMsg}</p>
				<c:remove var="deleteErrorMsg" scope="session"/>
			</c:if>
			
			
		<c:if test="${sessionScope.loginUser.userId == r.userId}">
		    <form action="DailyReportPostServlet" method="post">
		        <input type="hidden" name="action" value="dailyReportDelete">
		        <input type="hidden" name="reportId" value="${r.dailyReportId}">
		        <input type="submit" value="削除" class="action-btn">
		    </form>
		</c:if>
	</div>
		
<script>
	document.addEventListener('click', function (event) {
	    if (event.target.classList.contains('action-btn')) {
	        sessionStorage.setItem('OffsetTop', window.pageYOffset);
	    }
	});
</script>

<script>
	window.addEventListener('load', function () {
	
	    const offsetTop = sessionStorage.getItem('OffsetTop');
	
	    if (offsetTop !== null) {
	        window.scrollTo({
	            top: parseInt(offsetTop),
	            behavior: 'smooth'
	        });
	
	        sessionStorage.removeItem('OffsetTop');
	    }
	});
</script>

</c:forEach>
<div style="margin-top:20px; text-align:center;">

    <!-- 前へ -->
    <c:if test="${hasPrev}">
        <a href="dailyReportPage?page=${currentPage - 1}">← 前へ</a>
    </c:if>

    <!-- ページ番号 -->
    <c:forEach var="i" begin="1" end="${totalPage}">
        <c:choose>
            <c:when test="${i == currentPage}">
                <strong style="margin:0 5px;">${i}</strong>
            </c:when>
            <c:otherwise>
                <a href="dailyReportPage?page=${i}" style="margin:0 5px;">${i}</a>
            </c:otherwise>
        </c:choose>
    </c:forEach>

    <!-- 次へ -->
    <c:if test="${hasNext}">
        <a href="dailyReportPage?page=${currentPage + 1}">次へ →</a>
    </c:if>

</div>

	

</body>
</html>