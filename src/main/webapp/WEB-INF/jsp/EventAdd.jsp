<%@ page contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>イベント登録</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/EventAdd.css">
</head>

<body>

<header>
    <div class="header-top">
        <h1><a href="Main">ForzApp</a></h1>
        <div class="header-link">
            <a href="event">← イベント一覧</a>
        </div>
    </div>
</header>

<div class="form-container">

    <h1 class="page-title">📅 イベント登録</h1>

    <form action="eventAdd" method="post" class="form-card">

        <div class="form-group">
            <label>タイトル</label>
            <input type="text" name="title" placeholder="イベントタイトル">
        </div>

        <div class="form-group">
            <label>内容</label>
            <textarea name="content" placeholder="イベント内容"></textarea>
        </div>

        <div class="form-group">
            <label>エリア</label>
            <input type="text" name="area" placeholder="例：東京本社">
        </div>

        <div class="form-group">
            <label>開催日</label>
            <input type="date" name="eventDate">
        </div>

        <div class="btn-area">
            <button type="submit">登録</button>
            <a href="event" class="back-btn">戻る</a>
        </div>

    </form>

</div>

</body>
</html>