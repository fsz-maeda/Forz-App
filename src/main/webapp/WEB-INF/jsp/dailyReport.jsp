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
			<h1>日報</h1>
			<div class="header-link">
				<div>
					<a href="Main">メイン</a>
					<a href="Home">ログアウト</a>
				</div>
				<div>
					<form action="DailyReportPostServlet" method="get">
						<input type="hidden" name="action" value="post">
						<input type="submit" value="新規記事作成">
					</form>
				</div>
				<div>
					<form action="dailyReportPage" method="get">
					    <input type="text" name="keyword" placeholder="検索（タイトル or 種別）">
					    <input type="submit" value="検索">
					</form>
				</div>
			</div>
		</div>
	</header>
<a href="AttendanceServlet">勤怠</a>
	
	
		<c:if test="${not empty sessionScope.deleteErrorMsg }">
			<p>${sessionScope.deleteErrorMsg}</p>
			<c:remove var="deleteErrorMsg" scope="session"/>
		</c:if>
	
<c:forEach var="r" items="${reportList}">

    <div class="report-list">
        <h2>${r.title}</h2>
        
        <div class="report-content">
	        <div class="report-subcontent">
	        	<p>
				<fmt:formatDate value="${r.createdAt}" pattern="yyyy-MM-dd HH:mm"/>
				</p>
				<p>${r.reportType}</p>
		        <p>${r.userName}</p>
			</div>
	        <p>${r.content}</p>
	
			<form action="DailyReportLikeServlet" method="post">
			    <input type="hidden" name="dailyReportId" value="${r.dailyReportId}"/>
			    <c:choose>
			        <c:when test="${r.liked}">
			            <button type="submit" class="action-btn">❤</button>
			        </c:when>
			
			        <c:otherwise>
			            <button type="submit" class="action-btn">♡</button>
			        </c:otherwise>
			    </c:choose>
			    ${r.likes}
			</form>
		
	        <c:forEach var="c" items="${r.commentList}">
	           <div class="comment">
	               <p class="comment-text">${c.employeeName}：${c.commentText}</p>
	           <c:if test="${sessionScope.loginUser.employeeId == c.employeeId}">
				    <form action="DailyReportCommentServlet" method="post">
				        <input type="hidden" name="action" value="delete">
				        <input type="hidden" name="commentId" value="${c.commentId}">
				        <input type="hidden" name="reportId" value="${r.dailyReportId}">
				        <button type="submit" class="action-btn">削除</button>
				    </form>
				</c:if>
				</div>
	        </c:forEach>

		        
			<form action="DailyReportCommentServlet" method="post">
				<input type="hidden" name="action" value="insert">
			    <input type="hidden" name="reportId" value="${r.dailyReportId}">
			    <input type="text" name="comment" required>
			
			    <input type="submit" va
			    ]lue="コメント投稿" class="action-btn" >
			
			</form>
			
	
				
			<div class="delete-edit">
				<c:if test="${sessionScope.loginUser.employeeId == r.employeeId}">
				    <form action="DailyReportEditServlet" method="get">
				        <input type="hidden" name="reportId" value="${r.dailyReportId}">
				        <input type="submit" value="編集" class="action-btn">
				    </form>
				
				    <form action="DailyReportPostServlet" method="post">
				    	<input type="hidden" name="action" value="dailyReportDelete">
				        <input type="hidden" name="reportId" value="${r.dailyReportId}">
				        <input type="submit" value="削除" class="action-btn delete-btn">
				    </form>
				</c:if>
			</div>	
		</div>
	</div>
	

</c:forEach>

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