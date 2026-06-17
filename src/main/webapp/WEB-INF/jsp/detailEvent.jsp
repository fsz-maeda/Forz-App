<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>イベント詳細</title>
</head>
<body>
	<div class="event-card">
	
		<a href="event">イベント一覧へ</a>
		
        <h2>${event.title}</h2>
        
        <p class="event-content text-wrap">${event.content}</p>

        <div class="event-info">
        	<span>📍 ${event.area}</span>
            <span>📅 ${event.eventDate}</span>
            <span>❤ ${event.likes}</span>
        </div>

        <!-- いいね -->
        <form action="like" method="post">
            <input type="hidden" name="eventId" value="${event.eventId}">

            <c:choose>
                <c:when test="${event.liked}">
                    <button class="like-btn liked">いいね解除</button>
                </c:when>
                <c:otherwise>
                    <button class="like-btn">いいね</button>
                </c:otherwise>
            </c:choose>
        </form>

            <!-- コメント投稿 -->
        <form action="commentAdd" method="post" class="comment-form">
            <input type="hidden" name="eventId" value="${event.eventId}">
            <input type="text" name="comment" placeholder="コメント">
            <button>送信</button>
        </form>

            <!-- コメント一覧 -->
        <div class="comment-box">
            <h4>コメント（${event.commentList.size()}件）</h4>

            <c:if test="${empty event.commentList}">
                <p>まだコメントはありません</p>
            </c:if>

            <c:forEach var="comment" items="${event.commentList}">
            	<c:forEach var="employee" items="${employeeList}">
            		<c:if test="${comment.employeeId == employee.employeeId}">
           				<div class="comment-item">
                			👤 ${employee.name} : 
                        	💬 ${comment.comment}
                	</c:if>
                </c:forEach>

                <c:if test="${loginUser.employeeId == comment.employeeId}">
                	<form action="CommentDeleteServlet" method="post"nstyle="display:inline;" 
                	onsubmit="return confirm('削除しますか？');">

                		<input type="hidden" name="commentId" value="${comment.commentId}">
                    	<input type="submit" class="danger" value="削除">
                    </form>
                </c:if>
						</div>
			</c:forEach>
	</div>

	<!-- 編集・削除 -->
    <div class="event-actions">

 		<c:if test="${loginUser.employeeId == event.employeeId}">
			<form action="eventEdit" method="get">
				<input type="hidden" name="eventId" value="${event.eventId}">
                	<button>編集</button>
            </form>
        </c:if>

		<c:if test="${loginUser.employeeId == event.userId}">
			<form action="eventDelete" method="post" onsubmit="return confirm('削除しますか？');">
				<input type="hidden" name="eventId" value="${event.eventId}">
					<button class="danger">削除</button>
			</form>
		</c:if>

	</div>
</body>
</html>