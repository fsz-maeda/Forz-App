<%@ page contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>イベント編集</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/EventEdit.css">
</head>

<body>

<header>
    <div class="header-top">
        <h1><a href="Main">ForzApp</a></h1>
        <div class="header-link">
            <a href="event">イベント一覧</a>
            <button class="hamburger" onclick="toggleMenu()">☰</button>
			<jsp:include page="/WEB-INF/jsp/header.jsp" />
        </div>
    </div>
</header>


<div class="edit-container">

    <h1 class="page-title">✏️ イベント編集</h1>

    <div class="form-card">

        <form action="eventEdit" method="post">

            <input type="hidden" name="eventId" value="${event.eventId}">

            <div class="form-group">
                <label>タイトル</label>
                <input type="text" name="title" value="${event.title}" required>
            </div>

            <div class="form-group">
                <label>内容</label>
                <textarea name="content" required>${event.content}</textarea>
            </div>

            <div class="form-group">
                <label>エリア</label>
                <input type="text" name="area" value="${event.area}" required>
            </div>

            <div class="form-group">
                <label>開催日</label>
                <input type="date" name="eventDate" value="${event.eventDate}" required>
            </div>

            <div class="btn-area">
                <a href="event" class="back-btn">戻る</a>
                <button type="submit">更新</button>

            </div>

        </form>

    </div>

</div>

</body>
</html>