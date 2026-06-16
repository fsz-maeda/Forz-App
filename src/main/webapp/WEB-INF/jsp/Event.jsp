<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Event Page</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/Event.css">
</head>

<body>

<header>
    <div class="header-top">
        <h1><a href="Main">ForzApp</a></h1>

        <div class="header-link">
            <a href="eventAdd">＋イベント登録</a>
            <a href="calendar">カレンダー</a>
            <a href="Main">メインへ</a>
        </div>
    </div>
</header>

<<<<<<< HEAD
<div class="event-container">
=======
<c:forEach var="event" items="${eventList}">
	<div class="event-card">
		<h3>${event.title}</h3>
>>>>>>> refs/remotes/origin/kusumoto

<<<<<<< HEAD
    <h1 class="page-title">📅 イベント一覧</h1>
=======
		<p>${event.content}</p>
>>>>>>> refs/remotes/origin/kusumoto

<<<<<<< HEAD
    <!-- 検索 -->
    <form action="event" method="get" class="search-box">
        <input type="text" name="keyword" placeholder="タイトル検索">
        <button type="submit">検索</button>
    </form>
=======
		<p>Area : ${event.area}</p>
>>>>>>> refs/remotes/origin/kusumoto

<<<<<<< HEAD
    <c:forEach var="event" items="${eventList}">
        <div class="event-card">
=======
		<p>Date : ${event.eventDate}</p>
>>>>>>> refs/remotes/origin/kusumoto

<<<<<<< HEAD
            <h2>${event.title}</h2>
=======
		<p>いいね数：${event.likes}</p>
>>>>>>> refs/remotes/origin/kusumoto

<<<<<<< HEAD
            <p class="event-content">${event.content}</p>
=======
		<c:choose>

			<c:when test="${event.liked}">
				<form action="like" method="post">
					<input type="hidden" name="eventId" value="${event.eventId}">
					<input type="submit" value="いいね解除">
				</form>
			</c:when>

			<c:otherwise>
				<form action="like" method="post">
					<input type="hidden" name="eventId" value="${event.eventId}">
					<input type="submit" value="いいね">
				</form>
			</c:otherwise>

		</c:choose>

		<br>

		<!-- コメント投稿 -->
		<form action="commentAdd" method="post">

			<input type="hidden" name="eventId" value="${event.eventId}">

			<input type="text" name="comment" placeholder="コメントを入力"> <input
				type="submit" value="コメント">

		</form>

		<!-- コメント一覧 -->
		<h4>コメント一覧 （${event.commentList.size()}件）</h4>
		<c:if test="${empty event.commentList}">
			<p>まだコメントはありません</p>
		</c:if>
		<c:forEach var="comment" items="${event.commentList}">

			<p>
				${comment.comment}

				<c:if test="${loginUser.employeeId == comment.employeeId}">

					<form action="CommentDeleteServlet" method="post" style="display: inline;"
						onsubmit="return confirm('このコメントを削除しますか？');">

						<input type="hidden" name="commentId" value="${comment.commentId}">

						<input type="submit" value="削除">

					</form>
>>>>>>> refs/remotes/origin/kusumoto

<<<<<<< HEAD
            <div class="event-info">
                <span>📍 ${event.area}</span>
                <span>📅 ${event.eventDate}</span>
                <span>❤ ${event.likes}</span>
            </div>
=======
				</c:if>
>>>>>>> refs/remotes/origin/kusumoto

<<<<<<< HEAD
            <!-- いいね -->
            <form action="like" method="post" class="inline-form">
                <input type="hidden" name="eventId" value="${event.eventId}">
=======
			</p>
		</c:forEach>
		<c:if test="${loginUser.employeeId == event.employeeId}">
>>>>>>> refs/remotes/origin/kusumoto

<<<<<<< HEAD
                <c:choose>
                    <c:when test="${event.liked}">
                        <button class="like-btn liked">いいね解除</button>
                    </c:when>
                    <c:otherwise>
                        <button class="like-btn">いいね</button>
                    </c:otherwise>
                </c:choose>
            </form>
=======
			<form action="eventEdit" method="get">
				<input type="hidden" name="eventId" value="${event.eventId}">
				<input type="submit" value="編集">
			</form>
>>>>>>> refs/remotes/origin/kusumoto

<<<<<<< HEAD
            <!-- コメント -->
            <form action="commentAdd" method="post" class="comment-form">
                <input type="hidden" name="eventId" value="${event.eventId}">
                <input type="text" name="comment" placeholder="コメント">
                <button>送信</button>
            </form>
=======
		</c:if>
		<c:if test="${loginUser.employeeId == event.userId}">
			<form action="eventDelete" method="post"
				onsubmit="return confirm('このイベントを削除しますか？');">
>>>>>>> refs/remotes/origin/kusumoto

            <div class="comment-box">
                <h4>コメント（${event.commentList.size()}件）</h4>

<<<<<<< HEAD
                <c:if test="${empty event.commentList}">
                    <p class="no-comment">まだコメントはありません</p>
                </c:if>
=======
				<input type="submit" value="削除">
>>>>>>> refs/remotes/origin/kusumoto

<<<<<<< HEAD
                <c:forEach var="comment" items="${event.commentList}">
                    <div class="comment-item">
                        💬 ${comment.comment}
                    </div>
                </c:forEach>
            </div>
=======
			</form>
		</c:if>
>>>>>>> refs/remotes/origin/kusumoto

<<<<<<< HEAD
            <!-- 編集・削除 -->
            <div class="event-actions">
=======
	</div>
</c:forEach>
>>>>>>> refs/remotes/origin/kusumoto

<<<<<<< HEAD
                <c:if test="${loginUser.employeeId == event.employeeId}">
                    <form action="eventEdit" method="get">
                        <input type="hidden" name="eventId" value="${event.eventId}">
                        <button>編集</button>
                    </form>
                </c:if>
=======
>>>>>>> refs/remotes/origin/kusumoto

<<<<<<< HEAD
                <c:if test="${loginUser.employeeId == event.userId}">
                    <form action="eventDelete" method="post"
                          onsubmit="return confirm('削除しますか？');">
                        <input type="hidden" name="eventId" value="${event.eventId}">
                        <button class="danger">削除</button>
                    </form>
                </c:if>
=======
<!-- ページネーション -->
>>>>>>> refs/remotes/origin/kusumoto

<<<<<<< HEAD
            </div>
=======
<c:forEach var="i" begin="1" end="${totalPages}">
>>>>>>> refs/remotes/origin/kusumoto

<<<<<<< HEAD
        </div>
    </c:forEach>

    <!-- ページネーション -->
    <div class="pagination">
        <c:forEach var="i" begin="1" end="${totalPages}">
            <a href="event?page=${i}">${i}</a>
        </c:forEach>
    </div>

</div>

=======
	<a class="page-link" href="event?page=${i}"> ${i} </a>
</c:forEach>
>>>>>>> refs/remotes/origin/kusumoto
</body>
</html>